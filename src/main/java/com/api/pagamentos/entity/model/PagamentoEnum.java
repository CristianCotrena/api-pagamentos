package com.api.pagamentos.entity.model;

public enum PagamentoEnum {
    PAGAR("PAGAR"),
    RECEBER("RECEBER");

    private final String statusEscolhido;

    PagamentoEnum(String statusEscolhido) {

        this.statusEscolhido = statusEscolhido;
    }

    public static PagamentoEnum statusEscolhido(String statusEscolhido) {
        /**
        PagamentoEnum[] var1 = values();
        int var2 = var1.length;
         **/

        for (PagamentoEnum statusEnum : PagamentoEnum.values()) {
            if (statusEnum.getStatusEscolhido().equalsIgnoreCase(statusEscolhido)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Status incorreto.");
    }

    public String getStatusEscolhido() {
        return statusEscolhido;
    }

    /**
    public void setStatusEscolhido(String statusEscolhido) {
        this.statusEscolhido = statusEscolhido;
    }
     **/
}