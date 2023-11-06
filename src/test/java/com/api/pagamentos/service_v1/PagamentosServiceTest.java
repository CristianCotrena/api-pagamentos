package com.api.pagamentos.service_v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.dtos.CadastrarPagamentoResponseDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.mock.PagamentosModelBuilder;
import com.api.pagamentos.mock.PagamentosRequestDtoBuilder;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.service.v1.CadastrarPagamentosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@DisplayName("PagamentosService - Testes")
@ExtendWith(MockitoExtension.class)
public class PagamentosServiceTest {

    @MockBean
    private PagamentosRepository pagamentosRepository;

    @Autowired
    private CadastrarPagamentosService cadastrarPagamentosService;

    private CadastrarPagamentoRequestDto cadastrarPagamentoRequestDtoIdFuncionario;
    private CadastrarPagamentoRequestDto cadastrarPagamentoRequestDtoIdCliente;
    private CadastrarPagamentoRequestDto cadastrarPagamentoRequestDtoIdFornecedor;
    private PagamentosModel pagamentosModel;

    @BeforeEach
    public void setup() {

        pagamentosRepository = mock(PagamentosRepository.class);
        cadastrarPagamentosService = new CadastrarPagamentosService(pagamentosRepository);

        cadastrarPagamentoRequestDtoIdFuncionario = PagamentosRequestDtoBuilder.buildIdFuncionario();
        cadastrarPagamentoRequestDtoIdCliente = PagamentosRequestDtoBuilder.buildIdCliente();
        cadastrarPagamentoRequestDtoIdFornecedor = PagamentosRequestDtoBuilder.buildIdFornecedor();
    }


    @DisplayName("01 - Cadastrar Pagamentos Id Funcionario")
    @Test
    public void cadastrarPagamentos_SucessoIdFuncionario() {

        pagamentosModel = PagamentosModelBuilder.buildIdFuncionario();
        when(pagamentosRepository.save(any(PagamentosModel.class))).thenReturn(pagamentosModel);
        ResponseEntity<BaseDto<PagamentosModel>> resultado = cadastrarPagamentosService.cadastrarPagamentos(cadastrarPagamentoRequestDtoIdFuncionario);
        CadastrarPagamentoResponseDto cadastrarPagamentoResponseDto = new CadastrarPagamentoResponseDto(pagamentosModel.getId().toString());

        assertEquals(CREATED, resultado.getStatusCode());
        assertEquals(pagamentosModel.getId().toString(), cadastrarPagamentoResponseDto.getId());
        assertEquals("Pagamento cadastrado com sucesso.", resultado.getBody().getResultado().getDescricao());
    }

    @DisplayName("02 - Cadastrar Pagamentos Id Cliente")
    @Test
    public void cadastrarPagamentos_SucessoIdCliente() {

        pagamentosModel = PagamentosModelBuilder.buildIdCliente();
        when(pagamentosRepository.save(any(PagamentosModel.class))).thenReturn(pagamentosModel);
        ResponseEntity<BaseDto<PagamentosModel>> resultado = cadastrarPagamentosService.cadastrarPagamentos(cadastrarPagamentoRequestDtoIdCliente);
        CadastrarPagamentoResponseDto cadastrarPagamentoResponseDto = new CadastrarPagamentoResponseDto(pagamentosModel.getId().toString());

        assertEquals(CREATED, resultado.getStatusCode());
        assertEquals(pagamentosModel.getId().toString(), cadastrarPagamentoResponseDto.getId());
        assertEquals("Pagamento cadastrado com sucesso.", resultado.getBody().getResultado().getDescricao());
    }

    @DisplayName("03 - Cadastrar Pagamentos Id Fornecedor")
    @Test
    public void cadastrarPagamentos_SucessoIdFornecedor() {

        pagamentosModel = PagamentosModelBuilder.buildIdFornecedor();
        when(pagamentosRepository.save(any(PagamentosModel.class))).thenReturn(pagamentosModel);
        ResponseEntity<BaseDto<PagamentosModel>> resultado = cadastrarPagamentosService.cadastrarPagamentos(cadastrarPagamentoRequestDtoIdFornecedor);
        CadastrarPagamentoResponseDto cadastrarPagamentoResponseDto = new CadastrarPagamentoResponseDto(pagamentosModel.getId().toString());

        assertEquals(CREATED, resultado.getStatusCode());
        assertEquals(pagamentosModel.getId().toString(), cadastrarPagamentoResponseDto.getId());
        assertEquals("Pagamento cadastrado com sucesso.", resultado.getBody().getResultado().getDescricao());
    }
}
