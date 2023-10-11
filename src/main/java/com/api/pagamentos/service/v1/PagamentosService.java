package com.api.pagamentos.service.v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.builder.ResponseErrorBuilder;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.constants.MensagemDeErro;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.transforme.PagamentosTransforme;
import com.api.pagamentos.validation.PagamentosValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentosService {
    private PagamentosRepository pagamentosRepository;

    @Autowired
    public PagamentosService(PagamentosRepository pagamentosRepository) {
        this.pagamentosRepository = pagamentosRepository;
    }

    @Transactional
    public BaseDto cadastrarPagamentos(PagamentosRequestDto pagamentosRequestDto) {
        List<BaseErrorDto> erros = new PagamentosValidation().validate(pagamentosRequestDto);

        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, String.valueOf(erros));
            return resultado.get().getBody();
        }

        erros.addAll(verificarExistencia(pagamentosRequestDto));

        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.get().getBody();
        }

        var pagamentos = new PagamentosTransforme().transformarParaPagamentosModel(pagamentosRequestDto);

        var savedPagamentos = pagamentosRepository.save(pagamentos);

        return new ResponseSucessBuilder<>(HttpStatus.CREATED, savedPagamentos, "Pagamento cadastrado com sucesso.").get().getBody();
    }

    private List<BaseErrorDto> verificarExistencia(PagamentosRequestDto pagamentosRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        if (pagamentosRepository.existsById(pagamentosRequestDto.getIdFuncionario())) {
            erros.add(new BaseErrorDto("idFuncionário.", MensagemDeErro.RESGISTERED_FIELD));
        }
        if (pagamentosRepository.existsById(pagamentosRequestDto.getIdFornecedor())) {
            erros.add(new BaseErrorDto("idFornecedor", MensagemDeErro.RESGISTERED_FIELD));
        }
        if (pagamentosRepository.existsById(pagamentosRequestDto.getIdCliente())) {
            erros.add(new BaseErrorDto("idCliente", MensagemDeErro.RESGISTERED_FIELD));
        }
        if (pagamentosRepository.existsByStatusPagamento(pagamentosRequestDto.getStatusPagamento()).orElse(false)) {
            erros.add(new BaseErrorDto("statusPagamento.", MensagemDeErro.RESGISTERED_FIELD));
        }
        if (pagamentosRepository.existsByDescricao(pagamentosRequestDto.getDescricao()).orElse(false)) {
            erros.add(new BaseErrorDto("descricao.", MensagemDeErro.RESGISTERED_FIELD));
        }
        if (pagamentosRepository.existsByValor(pagamentosRequestDto.getValor()).orElse(false)) {
            erros.add(new BaseErrorDto("valor.", MensagemDeErro.RESGISTERED_FIELD));
        }
        if (pagamentosRepository.existsByData(pagamentosRequestDto.getData()).orElse(false)) {
            erros.add(new BaseErrorDto("data.", MensagemDeErro.RESGISTERED_FIELD));
        }
        if (pagamentosRepository.existsByStatus(pagamentosRequestDto.getStatus()).orElse(false)) {
            erros.add(new BaseErrorDto("status.", MensagemDeErro.RESGISTERED_FIELD));
        }
        return erros;
    }
}
