package com.api.pagamentos.mock;

import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PagamentosModelBuilder {
public static PagamentosModel build(){
    PagamentosModel resultado = new PagamentosModel();
    resultado.setId(UUID.fromString("415e42c7-9567-4665-aebd-e19cb564b772"));
    resultado.setIdFuncionario(UUID.fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
    resultado.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
    resultado.setDescricao("Pagamento pendente");
    resultado.setValor(150.00);
    resultado.setData(ZonedDateTime.parse("2025-10-09T17:21:17.189+00:00"));
    resultado.setStatus(1);

    return resultado;
}
}
