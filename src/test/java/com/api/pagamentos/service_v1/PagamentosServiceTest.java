package com.api.pagamentos.service_v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.constants.MensagemDeErro;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.mock.PagamentosModelBuilder;
import com.api.pagamentos.mock.PagamentosRequestDtoBuilder;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.service.v1.PagamentosService;
import com.api.pagamentos.validation.PagamentosValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        var request = PagamentosRequestDtoBuilder.build();
        var response = PagamentosModelBuilder.build();

        when(pagamentosRepository.save(any(PagamentosModel.class))).thenReturn(response);

        BaseDto<PagamentosModel> resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(response.getId(), resultado.getDados().getId());
        assertEquals(CREATED.value(), resultado.getResultado().getStatus());
        assertEquals("Pagamento cadastrado com sucesso.", resultado.getResultado().getDescricao());
    }

    @DisplayName("02 - Erro ao cadastrar id Cliente existente")
    @Test
    public void erroAoCadastrarIdClienteExistente() {
        var request = PagamentosRequestDtoBuilder.build();

        request.setIdCliente(randomUUID());

        when(pagamentosRepository.existsById(request.getIdCliente())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("03 - Erro ao cadastrar id Funcionário existente")
    @Test
    public void erroAoCadastrarIdFuncionarioExistente() {
        var request = PagamentosRequestDtoBuilder.build();

        request.setIdFuncionario(randomUUID());

        when(pagamentosRepository.existsById(request.getIdFuncionario())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("04 - Erro ao cadastrar id Fornecedor existente")
    @Test
    public void erroAoCadastrarIdFornecedorExistente() {
        var request = PagamentosRequestDtoBuilder.build();

        request.setIdFornecedor(randomUUID());

        when(pagamentosRepository.existsById(request.getIdFornecedor())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("05 - Erro ao cadastrar status de pagamento existente")
    @Test
    public void testarErroAoCadastrarStatusPagamentoExistente() {
        var request = PagamentosRequestDtoBuilder.build();

        request.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));

        when(pagamentosRepository.existsById(request.getIdFornecedor())).thenReturn(true);

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("06 - Erro ao cadastrar descrição existente")
    @Test
    public void testarErroAoCadastrarDescricaoExistente() {
        var request = PagamentosRequestDtoBuilder.build();

        request.setDescricao("Pagamento pendente");

        when(pagamentosRepository.existsById(request.getIdFornecedor())).thenReturn(true);

        var resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("07 - Erro ao cadastrar Data existente")
    @Test
    public void testarErroAoCadastrarDataExistente() {
        var request = PagamentosRequestDtoBuilder.build();

        request.setData(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));

        when(pagamentosRepository.existsByData(request.getData())).thenReturn(Optional.of(true));

        BaseDto resultado;
        resultado = pagamentosService.cadastrarPagamentos(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("08 - Erro na Validação")
    @Test
    public void testarErroDeValidacao() {
        var request = PagamentosRequestDtoBuilder.build();

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
    @Test
    void buscarPagamentoSucesso(){
        var response = PagamentosModelBuilder.build();
        var modelOptional = Optional.of(response);
        when(pagamentosRepository.findById(any(UUID.class))).thenReturn(modelOptional);
        ResponseEntity<BaseDto> resultado = pagamentosService.buscarPagamento
                (UUID.fromString("415e42c7-9567-4665-aebd-e19cb564b772"));
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK,resultado.getStatusCode());
    }
    @Test
    void buscarPagamentoSucessoFindById(){
        var model = PagamentosModelBuilder.build();
        var modelOptional = Optional.of(model);
        when(pagamentosRepository.findById(any(UUID.class))).thenReturn(modelOptional);
        PagamentosModel resultado = pagamentosRepository.findById
                (UUID.fromString("415e42c7-9567-4665-aebd-e19cb564b772")).get();
        assertNotNull(resultado);
        assertEquals(PagamentosModel.class, resultado.getClass());
        assertEquals(UUID.fromString("415e42c7-9567-4665-aebd-e19cb564b772"),resultado.getId());
        assertEquals(UUID.fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"),resultado.getIdFuncionario());
        assertEquals(PagamentoEnum.valueOf("PAGAR"),resultado.getStatusPagamento());
        assertEquals("Pagamento pendente",resultado.getDescricao());
        assertEquals(150,resultado.getValor());
        assertEquals(ZonedDateTime.parse("2025-10-09T17:21:17.189+00:00"),resultado.getData());
        assertEquals(1,resultado.getStatus());
    }
    @Test
    void erroBuscarPagamento(){
        var response = PagamentosModelBuilder.build();
        var modelOptional = Optional.of(response);
        when(pagamentosRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<BaseDto> resultado = pagamentosService.buscarPagamento
                (UUID.fromString("9d9affac-4c18-4b40-9de5-846418072fc1"));
        assertEquals(HttpStatus.NOT_FOUND,resultado.getStatusCode());
        assertEquals(MensagemDeErro.NOT_FOUND,resultado.getBody().getResultado().getDescricao());
    }
    @Test
    void erroBuscarPagamentoFindById(){
        var response = PagamentosModelBuilder.build();
        var modelOptional = Optional.of(response);
        when(pagamentosRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        Optional<PagamentosModel> resultado = pagamentosRepository.findById
                (UUID.fromString("1ad576b0-71be-426a-916c-58ed1a74d0de"));
        assertEquals(Optional.empty(),resultado);

    }
}



