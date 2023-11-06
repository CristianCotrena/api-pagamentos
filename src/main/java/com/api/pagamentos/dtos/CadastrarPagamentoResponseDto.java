package com.api.pagamentos.dtos;

import com.api.pagamentos.entity.model.PagamentoEnum;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CadastrarPagamentoResponseDto implements Serializable {

    private String id;

    public CadastrarPagamentoResponseDto() {
    }

    public CadastrarPagamentoResponseDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
