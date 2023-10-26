package com.api.pagamentos.service_v1;


import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.dtos.ListarPagamentosResponseDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.service.v1.ListarPagamentosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class ListarPagamentosServiceTest {
    @InjectMocks
    private ListarPagamentosService listarPagamentosService;
    @Mock
    private PagamentosRepository pagamentosRepository;




    @BeforeEach
    void setUp() {
        pagamentosRepository = mock(PagamentosRepository.class);
        listarPagamentosService = new ListarPagamentosService(pagamentosRepository);
    }
    @Test
    public void listarPagamentosTestErro() {
        List<PagamentosModel> pagamento = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<PagamentosModel> clientePagina = new PageImpl<>(pagamento, pageable, pagamento.size());
        when(pagamentosRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(clientePagina);
        ResponseEntity<BaseDto> responseEntity = listarPagamentosService.listarPagamentos(null, null, null, null,0);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

        @Test
        public void testarListaPagamentosSucesso() {
            UUID idFuncionario = UUID.fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5");
            List<PagamentosModel> pagamentos = new ArrayList<>();
            pagamentos.add(new PagamentosModel(idFuncionario, null, null, PagamentoEnum.PAGAR, "Pagamento pendente", 150.00, null));
            pagamentos.add(new PagamentosModel(idFuncionario, null, null, PagamentoEnum.PAGAR, "Pagamento pendente", 150.00, null));
            Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("data")));
            when(pagamentosRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(new PageImpl<>(pagamentos));
            ResponseEntity<Page<PagamentosModel>> responseEntity = listarPagamentosService.listarPagamentos(null, idFuncionario, null, PagamentoEnum.PAGAR, 0);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        }
}

