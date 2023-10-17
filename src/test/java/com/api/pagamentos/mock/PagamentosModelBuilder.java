package com.api.pagamentos.mock;

import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PagamentosModelBuilder {
public static PagamentosModel build(){
    PagamentosModel resultado = new PagamentosModel();
    var request = new PagamentosRequestDto();
    request.setIdFuncionario(UUID.randomUUID());
    request.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
    request.setDescricao("Pagamento pendente");
    request.setValor(150.00);
    request.setData(ZonedDateTime.parse("2025-10-09T17:21:17.189+00:00"));
    request.setStatus(1);
    return resultado;
}
}
