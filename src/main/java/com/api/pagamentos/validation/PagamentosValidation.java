package com.api.pagamentos.validation;

import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.constants.MensagemDeErro;
import com.api.pagamentos.dtos.PagamentosRequestDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentosValidation {
    public List<BaseErrorDto> validate(PagamentosRequestDto pagamentosRequestDto) {
        List<BaseErrorDto> erros = validateCamposRequeridos(pagamentosRequestDto);
        return !erros.isEmpty() ? erros : validateCamposInvalidos(pagamentosRequestDto, erros);
    }

        private List<BaseErrorDto> validateCamposRequeridos(PagamentosRequestDto pagamentosRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        //Todo: Campos obrigatórios
        if (pagamentosRequestDto.getIdFuncionario() == null && pagamentosRequestDto.getIdFornecedor() == null && pagamentosRequestDto.getIdCliente() == null) {
            erros.add(new BaseErrorDto("idFuncionario", MensagemDeErro.EMPTY_FIELD));
            erros.add(new BaseErrorDto("idFornecedor", MensagemDeErro.EMPTY_FIELD));
            erros.add(new BaseErrorDto("idCliente", MensagemDeErro.EMPTY_FIELD));
        }
        if (pagamentosRequestDto.getIdFuncionario() != null && pagamentosRequestDto.getIdFornecedor() != null && pagamentosRequestDto.getIdCliente() != null) {
            erros.add(new BaseErrorDto("idFuncionario", "Os campos funcionário, fornecedor e cliente não podem aparecer simultaneamente."));
            erros.add(new BaseErrorDto("idFornecedor", "Os campos funcionário, fornecedor e cliente não podem aparecer simultaneamente."));
            erros.add(new BaseErrorDto("idCliente", "Os campos funcionário, fornecedor e cliente não podem aparecer simultaneamente."));
        }
        if (pagamentosRequestDto.getStatusPagamento() == null) {
            erros.add(new BaseErrorDto("statusPagamento", MensagemDeErro.EMPTY_FIELD));
        }
        if (pagamentosRequestDto.getDescricao().isEmpty()) {
            erros.add(new BaseErrorDto("descricao", MensagemDeErro.EMPTY_FIELD));
        }
        if (pagamentosRequestDto.getData() == null) {
            erros.add(new BaseErrorDto("data", MensagemDeErro.EMPTY_FIELD));
        }
        return erros;
    }

    //Todo: Campos inválidos
    private List<BaseErrorDto> validateCamposInvalidos(PagamentosRequestDto pagamentosRequestDto, List<BaseErrorDto> erros) {

        if (pagamentosRequestDto.getData().isBefore(ZonedDateTime.now())) {
            erros.add(new BaseErrorDto("data", MensagemDeErro.INVALID_FIELD));
        }
        if (pagamentosRequestDto.getValor() ==  0) {
            erros.add(new BaseErrorDto("valor", MensagemDeErro.INVALID_FIELD));
        }
        return erros;
    }
}
