package com.api.pagamentos.validation;

import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.constants.MensagemDeErro;
import com.api.pagamentos.core.date.DateUtils;
import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CadastrarPagamentosValidation {
    public List<BaseErrorDto> validate(CadastrarPagamentoRequestDto cadastrarPagamentoRequestDto) {
        List<BaseErrorDto> erros = validateCamposRequeridos(cadastrarPagamentoRequestDto);
        return !erros.isEmpty() ? erros : validateCamposInvalidos(cadastrarPagamentoRequestDto, erros);
    }

    public List<BaseErrorDto> validateCamposRequeridos(CadastrarPagamentoRequestDto cadastrarPagamentoRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        if (
                (cadastrarPagamentoRequestDto.getIdFuncionario() == null || cadastrarPagamentoRequestDto.getIdFuncionario().isEmpty())
                        && (cadastrarPagamentoRequestDto.getIdFornecedor() == null || cadastrarPagamentoRequestDto.getIdFornecedor().isEmpty())
                        && (cadastrarPagamentoRequestDto.getIdCliente() == null || cadastrarPagamentoRequestDto.getIdCliente().isEmpty())) {
            erros.add(new BaseErrorDto("idCliente, idFornecedor, idFuncionario", MensagemDeErro.EMPTY_FIELD));
        } else if (
                (cadastrarPagamentoRequestDto.getIdFuncionario() != null && !cadastrarPagamentoRequestDto.getIdFuncionario().isEmpty())
                        && (cadastrarPagamentoRequestDto.getIdFornecedor() != null && !cadastrarPagamentoRequestDto.getIdFornecedor().isEmpty())
                        && (cadastrarPagamentoRequestDto.getIdCliente() != null && !cadastrarPagamentoRequestDto.getIdCliente().isEmpty())) {
            erros.add(new BaseErrorDto("idCliente, idFornecedor, idFuncionario", "Os campos funcionário, fornecedor e cliente não podem aparecer simultaneamente."));
        } else if (
                (cadastrarPagamentoRequestDto.getIdFuncionario() != null && !cadastrarPagamentoRequestDto.getIdFuncionario().isEmpty())
                        && (cadastrarPagamentoRequestDto.getIdFornecedor() != null && !cadastrarPagamentoRequestDto.getIdFornecedor().isEmpty())) {
            erros.add(new BaseErrorDto("idFornecedor, idFuncionario", "Os campos funcionário e fornecedor não podem aparecer simultaneamente."));
        } else if (
                (cadastrarPagamentoRequestDto.getIdFuncionario() != null && !cadastrarPagamentoRequestDto.getIdFuncionario().isEmpty())
                        && (cadastrarPagamentoRequestDto.getIdCliente() != null && !cadastrarPagamentoRequestDto.getIdCliente().isEmpty())) {
            erros.add(new BaseErrorDto("idCliente, idFuncionario", "Os campos funcionário e cliente não podem aparecer simultaneamente."));
        } else if (
                (cadastrarPagamentoRequestDto.getIdFornecedor() != null && !cadastrarPagamentoRequestDto.getIdFornecedor().isEmpty())
                        && (cadastrarPagamentoRequestDto.getIdCliente() != null && !cadastrarPagamentoRequestDto.getIdCliente().isEmpty())) {
            erros.add(new BaseErrorDto("idCliente, idFornecedor", "Os campos fornecedor e cliente não podem aparecer simultaneamente."));
        }
        if (cadastrarPagamentoRequestDto.getStatusPagamento() == null || cadastrarPagamentoRequestDto.getStatusPagamento().isEmpty()) {
            erros.add(new BaseErrorDto("statusPagamento", MensagemDeErro.EMPTY_FIELD));
        }
        if (cadastrarPagamentoRequestDto.getDescricao() == null || cadastrarPagamentoRequestDto.getDescricao().isEmpty()) {
            erros.add(new BaseErrorDto("descricao", MensagemDeErro.EMPTY_FIELD));
        }
        if (cadastrarPagamentoRequestDto.getValor() == null || cadastrarPagamentoRequestDto.getValor().isEmpty()) {
            erros.add(new BaseErrorDto("valor", MensagemDeErro.EMPTY_FIELD));
        }
        if (cadastrarPagamentoRequestDto.getData() == null || cadastrarPagamentoRequestDto.getData().isEmpty()) {
            erros.add(new BaseErrorDto("data", MensagemDeErro.EMPTY_FIELD));
        }
        return erros;
    }

    public List<BaseErrorDto> validateCamposInvalidos(CadastrarPagamentoRequestDto cadastrarPagamentoRequestDto, List<BaseErrorDto> erros) {

        if ((cadastrarPagamentoRequestDto.getIdCliente() != null) && (!cadastrarPagamentoRequestDto.getIdCliente().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(cadastrarPagamentoRequestDto.getIdCliente()).matches()) {
                erros.add(new BaseErrorDto("idCliente", MensagemDeErro.INVALID_FIELD));
            }
        }
        if ((cadastrarPagamentoRequestDto.getIdFornecedor() != null) && (!cadastrarPagamentoRequestDto.getIdFornecedor().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(cadastrarPagamentoRequestDto.getIdFornecedor()).matches()) {
                erros.add(new BaseErrorDto("idFornecedor", MensagemDeErro.INVALID_FIELD));
            }
        }
        if ((cadastrarPagamentoRequestDto.getIdFuncionario() != null) && (!cadastrarPagamentoRequestDto.getIdFuncionario().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(cadastrarPagamentoRequestDto.getIdFuncionario()).matches()) {
                erros.add(new BaseErrorDto("idFuncionario", MensagemDeErro.INVALID_FIELD));
            }
        }
        if ((!cadastrarPagamentoRequestDto.getStatusPagamento().equalsIgnoreCase("PAGAR"))
                && (!cadastrarPagamentoRequestDto.getStatusPagamento().equalsIgnoreCase("RECEBER"))) {
            erros.add(new BaseErrorDto("status", MensagemDeErro.INVALID_FIELD));
        }
        if (!new DateUtils().dataIso(cadastrarPagamentoRequestDto.getData())) {
            erros.add(new BaseErrorDto("data", MensagemDeErro.INVALID_FIELD));
        } else {
            if (!new DateUtils().dataMenorQueAtual(cadastrarPagamentoRequestDto.getData())) {
                erros.add(new BaseErrorDto("data", MensagemDeErro.INVALID_FIELD));
            }
        }
        double testeValor = 0;
        if (cadastrarPagamentoRequestDto.getValor() != null && !cadastrarPagamentoRequestDto.getValor().isEmpty()) {
            if (cadastrarPagamentoRequestDto.getValor().contains(",")) {
                String valorSubstituto = cadastrarPagamentoRequestDto.getValor().replace(",", ".");
                cadastrarPagamentoRequestDto.setValor(valorSubstituto);
            }
            if ((testeValor = Double.parseDouble(cadastrarPagamentoRequestDto.getValor())) <= 0) {
                erros.add(new BaseErrorDto("valor", MensagemDeErro.INVALID_FIELD));
            }
        }
        return erros;
    }
}
