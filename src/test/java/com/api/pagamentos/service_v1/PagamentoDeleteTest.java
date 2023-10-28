package com.api.pagamentos.service_v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.service.v1.DeletarPagamentosService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PagamentoDeleteTest {

    @InjectMocks
    private DeletarPagamentosService deletarPagamentosService;

    @Mock
    private PagamentosRepository pagamentoRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInativarPagamentoExistente() {
        UUID id = UUID.randomUUID();
        PagamentosModel clienteExistente = new PagamentosModel();
        when(pagamentoRepository.findByid(id)).thenReturn(Optional.of(clienteExistente));
        ResponseEntity<? extends BaseDto<? extends Object>> result = deletarPagamentosService.inativarPagamento(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testInativarPagamentoNaoExistente() {
        UUID id = UUID.randomUUID();
        when(pagamentoRepository.findByid(id)).thenReturn(Optional.empty());

        ResponseEntity<? extends BaseDto<? extends Object>> result = deletarPagamentosService.inativarPagamento(id);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}


