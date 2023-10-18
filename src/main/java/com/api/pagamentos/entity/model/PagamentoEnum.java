package com.api.pagamentos.entity.model;

public enum PagamentoEnum {
    PAGAR("PAGAR"),
    RECEBER("RECEBER");

    String statusEscolhido;

    PagamentoEnum(String statusEscolhido) {
        this.statusEscolhido = statusEscolhido;
    }

    public static PagamentoEnum statusEscolhido(String statusEscolhido) {
        PagamentoEnum[] var1 = values();
        int var2 = var1.length;

        for (PagamentoEnum statusEnum : var1) {
            if (statusEnum.getStatusEscolhido().equals(statusEscolhido)) {
                return statusEnum;
            }
        }

        return null;
    }

    public String getStatusEscolhido() {
        return statusEscolhido;
    }

    public void setStatusEscolhido(String statusEscolhido) {
        this.statusEscolhido = statusEscolhido;
    }
}