package com.api.pagamentos.service_v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.service.v1.PagamentosService;
import com.api.pagamentos.validation.PagamentosValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@DisplayName("PagamentosService - Testes")
@ExtendWith(MockitoExtension.class)
public class PagamentosServiceTest {

    PagamentosRepository pagamentosRepository = mock(PagamentosRepository.class);
    PagamentosValidation pagamentosValidation = mock(PagamentosValidation.class);
    PagamentosService pagamentosService = new PagamentosService(pagamentosRepository);

    @DisplayName("01 - Cadastrar Pagamentos")
    @Test
    public void cadastrarPagamentos() {
        var request = new PagamentosRequestDto();
        var id = randomUUID();

        request.setIdFuncionario(id);
        request.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
        request.setDescricao("Pagamento pendente");
        request.setValor(150.00);
        request.setData(ZonedDateTime.parse("2025-10-09T17:21:17.189+00:00"));
        request.setStatus(1);

        var resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(CREATED.value(), resultado.getResultado().getStatus());
        assertEquals("Pagamento cadastrado com sucesso.", resultado.getResultado().getDescricao());
    }

    @DisplayName("02 - Erro ao cadastrar id Cliente existente")
    @Test
    public void erroAoCadastrarIdClienteExistente() {
        var request = new PagamentosRequestDto();

        request.setIdCliente(randomUUID());

        when(pagamentosRepository.existsById(request.getIdCliente())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("03 - Erro ao cadastrar id Funcionário existente")
    @Test
    public void erroAoCadastrarIdFuncionarioExistente() {
        var request = new PagamentosRequestDto();

        request.setIdFuncionario(randomUUID());

        when(pagamentosRepository.existsById(request.getIdFuncionario())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("04 - Erro ao cadastrar id Fornecedor existente")
    @Test
    public void erroAoCadastrarIdFornecedorExistente() {
        var request = new PagamentosRequestDto();

        request.setIdFornecedor(randomUUID());

        when(pagamentosRepository.existsById(request.getIdFornecedor())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("05 - Erro ao cadastrar status de pagamento existente")
    @Test
    public void testarErroAoCadastrarStatusPagamentoExistente() {
        var request = new PagamentosRequestDto();

        request.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));

        when(pagamentosRepository.existsById(request.getIdFornecedor())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("06 - Erro ao cadastrar descrição existente")
    @Test
    public void testarErroAoCadastrarDescricaoExistente() {
        var request = new PagamentosRequestDto();

        request.setDescricao("Pagamento pendente");

        when(pagamentosRepository.existsById(request.getIdFornecedor())).thenReturn(true);

        var resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("07 - Erro ao cadastrar Data existente")
    @Test
    public void testarErroAoCadastrarDataExistente() {
        var request = new PagamentosRequestDto();

        request.setData(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));

        when(pagamentosRepository.existsByData(request.getData())).thenReturn(Optional.of(true));

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("08 - Erro na Validação")
    @Test
    public void testarErroDeValidacao() {
        var request = new PagamentosRequestDto();

        request.setIdFuncionario(fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
        request.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
        request.setDescricao("Pagamento pendente");
        request.setValor(150.00);
        request.setData(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));
        request.setStatus(1);

        List<BaseErrorDto> validationErrors = new ArrayList<>();
        validationErrors.add(new BaseErrorDto("campo", "mensagem de erro"));

        when(pagamentosRepository.existsByData(request.getData())).thenReturn(Optional.of(true));
        when(pagamentosValidation.validate(request)).thenReturn(validationErrors);

        var resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }
}



