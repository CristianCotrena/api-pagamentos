package com.api.pagamentos.validation;
import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.constants.MensagemDeErro;
import com.api.pagamentos.dtos.ListarPagamentosRequestDto;
import com.api.pagamentos.dtos.ListarPagamentosResponseDto;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class ListarPagamentosValidation {
    public List<BaseErrorDto> validate(ListarPagamentosRequestDto request) {
        List<BaseErrorDto> erros = validateCamposRequeridos(request);
        return !erros.isEmpty() ? erros : validateCamposInvalidos(request, erros);
    }
    private List<BaseErrorDto> validateCamposRequeridos(ListarPagamentosRequestDto request) {
        List<BaseErrorDto> erros = new ArrayList<>();
        long camposPreenchidos = Arrays.asList(request.getIdCliente(), request.getIdFuncionario(), request.getIdFornecedor())
                .stream()
                .filter(Objects::nonNull)
                .count();
        if (camposPreenchidos != 1) {
            erros.add(new BaseErrorDto("[idFuncionario, idFornecedor, idCliente]", MensagemDeErro.ONLY_ONE));
        }

        return erros;
    }
    private List<BaseErrorDto> validateCamposInvalidos(ListarPagamentosRequestDto request, List<BaseErrorDto> erros) {
        List<BaseErrorDto> resultErros = erros;
        if (request.getStatusPagamento() != null && PagamentoEnum.statusEscolhido(request.getStatusPagamento()) == null) {
            erros.add(new BaseErrorDto("statusPagamento", "Status do pagamento é invalido"));
        }
        if (request.getPagina() < 0){
            erros.add(new BaseErrorDto("Pagina", MensagemDeErro.INVALID_FIELD));
        }
        return erros;
    }
}