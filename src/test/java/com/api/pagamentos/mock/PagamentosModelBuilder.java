package com.api.pagamentos.mock;

import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class PagamentosModelBuilder {

    public static PagamentosModel buildIdFuncionario(){
        PagamentosModel resultado = new PagamentosModel();
        resultado.setId(UUID.randomUUID());
        resultado.setIdFuncionario(UUID.randomUUID());
        resultado.setIdCliente(null);
        resultado.setIdFornecedor(null);
        resultado.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
        resultado.setDescricao("Pagamento pendente");
        resultado.setValor(150.00);

        Date data = new Date(2023 - 1900, 9 - 1, 9);
        resultado.setData(data);

        resultado.setStatus(1);

        return resultado;
    }

    public static PagamentosModel buildIdCliente(){
        PagamentosModel resultado = new PagamentosModel();
        resultado.setId(UUID.randomUUID());
        resultado.setIdFuncionario(null);
        resultado.setIdCliente(UUID.randomUUID());
        resultado.setIdFornecedor(null);
        resultado.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
        resultado.setDescricao("Pagamento pendente");
        resultado.setValor(150.00);

        Date data = new Date(2023 - 1900, 9 - 1, 9);
        resultado.setData(data);

        resultado.setStatus(1);

        return resultado;
    }

    public static PagamentosModel buildIdFornecedor(){
        PagamentosModel resultado = new PagamentosModel();
        resultado.setId(UUID.randomUUID());
        resultado.setIdFuncionario(null);
        resultado.setIdCliente(null);
        resultado.setIdFornecedor(UUID.randomUUID());
        resultado.setStatusPagamento(PagamentoEnum.valueOf("PAGAR"));
        resultado.setDescricao("Pagamento pendente");
        resultado.setValor(150.00);

        Date data = new Date(2023 - 1900, 9 - 1, 9);
        resultado.setData(data);

        resultado.setStatus(1);

        return resultado;
    }
}
