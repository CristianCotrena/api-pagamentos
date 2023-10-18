package com.api.pagamentos.mock;

import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PagamentosRequestDtoBuilder {
    public static PagamentosRequestDto build(){
        var request = new PagamentosRequestDto();
        request.setIdFuncionario(UUID.fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
        request.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
        request.setDescricao("Pagamento pendente");
        request.setValor(150.00);
        request.setData(ZonedDateTime.parse("2025-10-09T17:21:17.189+00:00"));
        request.setStatus(1);
        return request;
    }
}
