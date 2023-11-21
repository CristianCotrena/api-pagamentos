package com.api.pagamentos.mock;

import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PagamentosRequestDtoBuilder {

    public static CadastrarPagamentoRequestDto buildIdFuncionario(){
        var request = new CadastrarPagamentoRequestDto();
        request.setIdFuncionario(UUID.randomUUID().toString());
        request.setIdCliente(null);
        request.setIdFornecedor(null);
        request.setStatusPagamento(String.valueOf(PagamentoEnum.valueOf("PAGAR")));
        request.setDescricao("Pagamento pendente");
        request.setValor("150.00");
        request.setData("2023-10-09");
        request.setStatus(1);
        return request;
    }

    public static CadastrarPagamentoRequestDto buildIdCliente(){
        var request = new CadastrarPagamentoRequestDto();
        request.setIdFuncionario(null);
        request.setIdCliente(UUID.randomUUID().toString());
        request.setIdFornecedor(null);
        request.setStatusPagamento(String.valueOf(PagamentoEnum.valueOf("PAGAR")));
        request.setDescricao("Pagamento pendente");
        request.setValor("150.00");
        request.setData("2023-10-09");
        request.setStatus(1);
        return request;
    }

    public static CadastrarPagamentoRequestDto buildIdFornecedor(){
        var request = new CadastrarPagamentoRequestDto();
        request.setIdFuncionario(null);
        request.setIdCliente(null);
        request.setIdFornecedor(UUID.randomUUID().toString());
        request.setStatusPagamento(String.valueOf(PagamentoEnum.valueOf("PAGAR")));
        request.setDescricao("Pagamento pendente");
        request.setValor("150.00");
        request.setData("2023-10-09");
        request.setStatus(1);
        return request;
    }
}
