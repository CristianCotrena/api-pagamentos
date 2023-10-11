package com.api.pagamentos.entity.model;

public enum PagamentoEnum {
    PAGAR("PAGAR"),
    RECEBER("RECEBER");

    String statusEscolhido;

    PagamentoEnum(String statusEscolhido) {
        this.statusEscolhido = statusEscolhido;
    }

    public static PagamentoEnum cargoSelecionado(String statusEscolhido) {
        PagamentoEnum[] var1 = values();
        int var2 = var1.length;

        for (PagamentoEnum cargosEnum : var1) {
            if (cargosEnum.getStatusEscolhido().equals(statusEscolhido)) {
                return cargosEnum;
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