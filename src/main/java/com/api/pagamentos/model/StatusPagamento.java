package com.api.pagamentos.model;

public enum StatusPagamento {
    PAGAR, RECEBER;
    String statusPagamento;

    StatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    StatusPagamento() {
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
