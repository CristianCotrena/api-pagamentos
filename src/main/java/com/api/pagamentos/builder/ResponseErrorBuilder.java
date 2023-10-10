package com.api.pagamentos.builder;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.base.dto.BaseResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ResponseErrorBuilder {
    private ResponseEntity<BaseDto<Void>> resultado;

    public ResponseErrorBuilder(HttpStatus status, String mensagem) {
        BaseResultDto resultado = new BaseResultDto(status.value(), mensagem);
        BaseDto<Void> baseDto = new BaseDto<>(null, new ArrayList<>(), resultado);
        this.resultado = ResponseEntity.status(status.value()).body(baseDto);
    }

    public ResponseErrorBuilder(HttpStatus status, List<BaseErrorDto> erros) {
        BaseResultDto resultado = new BaseResultDto(status.value(), status.getReasonPhrase());
        BaseDto<Void> baseDto = new BaseDto<>(null, erros, resultado);
        this.resultado = ResponseEntity.status(status.value()).body(baseDto);
    }

    public ResponseErrorBuilder(HttpStatus status, String mensagem, List<BaseErrorDto> erros) {
        BaseResultDto resultado = new BaseResultDto(status.value(), mensagem);
        BaseDto<Void> baseDto = new BaseDto<>(null, erros, resultado);
        this.resultado = ResponseEntity.status(status.value()).body(baseDto);
    }

    public ResponseEntity<BaseDto<Void>> get() {
        return resultado;
    }
}

