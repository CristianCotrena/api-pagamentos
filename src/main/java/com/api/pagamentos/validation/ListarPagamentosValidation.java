package com.api.pagamentos.validation;

import java.util.UUID;

public class ListarPagamentosValidation {

    public boolean validarId(UUID id) {
        return id != null;
    }

    public boolean validarStatusPagamento(String statusPagamento) {
        return statusPagamento == null || statusPagamento.equals("PAGAR") || statusPagamento.equals("RECEBER");
    }
}