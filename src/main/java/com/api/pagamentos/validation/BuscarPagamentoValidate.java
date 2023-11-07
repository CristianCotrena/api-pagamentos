package com.api.pagamentos.validation;

import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.constants.MensagemDeErro;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BuscarPagamentoValidate {

    public List<BaseErrorDto> validarParametros(String id) {

        List<BaseErrorDto> erros = new ArrayList<>();

        if (id == null || id.isEmpty()) {
            erros.add(new BaseErrorDto("id", MensagemDeErro.EMPTY_FIELD));
        }
        if (id != null && !id.isEmpty()) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(id).matches()) {
                erros.add(new BaseErrorDto("id", MensagemDeErro.INVALID_FIELD));
            }
        }
        return erros;
    }
}
