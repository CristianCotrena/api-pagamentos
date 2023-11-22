package com.api.pagamentos.service_v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.dtos.BuscarPagamentoResponseDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.mock.PagamentosModelBuilder;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.service.v1.BuscarPagamentoService;
import com.api.pagamentos.transforme.PagamentosTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarPagamentoServiceTests {

    @MockBean
    private PagamentosRepository pagamentosRepository;

    @Autowired
    private BuscarPagamentoService buscarPagamentoService;

    private BuscarPagamentoResponseDto buscarPagamentoResponseDto = new BuscarPagamentoResponseDto();
    private PagamentosModel pagamentosModel;

    @BeforeEach
    public void setup() {
        pagamentosRepository = mock(PagamentosRepository.class);
        buscarPagamentoService = new BuscarPagamentoService(pagamentosRepository);
    }

    @Test
    public void testeBuscarPagamento_Sucesso() {

        pagamentosModel = PagamentosModelBuilder.buildIdCliente();
        when(pagamentosRepository.findById(any(UUID.class))).thenReturn(Optional.of(pagamentosModel));

        ResponseEntity<BaseDto<PagamentosModel>> resultado = buscarPagamentoService.buscarPagamento(pagamentosModel.getId().toString());
        BuscarPagamentoResponseDto resposta = new BuscarPagamentoResponseDto();
        Optional<PagamentosModel> pagamentosModelOptional = pagamentosRepository.findById(pagamentosModel.getId());
        resposta = new PagamentosTransformer().transformerBuscarPagamentos(pagamentosModelOptional);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals("Pagamento encontrado.", resultado.getBody().getResultado().getDescricao());
        assertEquals(pagamentosModel.getId(), resposta.getId());
        assertEquals(pagamentosModel.getIdCliente(), resposta.getIdCliente());
        assertEquals(pagamentosModel.getIdFornecedor(), resposta.getIdFornecedor());
        assertEquals(pagamentosModel.getIdFuncionario(), resposta.getIdFuncionario());
        assertEquals(pagamentosModel.getStatusPagamento().toString(), resposta.getStatusPagamento());
        assertEquals(pagamentosModel.getDescricao(), resposta.getDescricao());
        assertEquals(pagamentosModel.getValor(), resposta.getValor());
        assertEquals(pagamentosModel.getData(), resposta.getData());
    }

    @Test
    public void testeBuscarPagamento_FracassoIdNaoExistente() {

        pagamentosModel = PagamentosModelBuilder.buildIdCliente();
        when(pagamentosRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResponseEntity<BaseDto<PagamentosModel>> resultado = buscarPagamentoService.buscarPagamento(pagamentosModel.getId().toString());

        assertEquals(HttpStatus.BAD_REQUEST, resultado.getStatusCode());
        assertEquals("id", resultado.getBody().getErros().get(0).getCampo());
        assertEquals("Nenhum pagamento encontrado", resultado.getBody().getErros().get(0).getMensagem());
    }
}
