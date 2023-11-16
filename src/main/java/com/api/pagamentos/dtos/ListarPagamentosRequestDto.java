package com.api.pagamentos.dtos;

import com.api.pagamentos.entity.model.PagamentoEnum;

import java.util.UUID;

public class ListarPagamentosRequestDto {
    public UUID idCliente;
    public UUID idFuncionario;
    public UUID idFornecedor;
    public String statusPagamento;
    public int pagina;
    public ListarPagamentosRequestDto(UUID idCliente, UUID idFuncionario, UUID idFornecedor, String statusPagamento, int pagina) {
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.statusPagamento = statusPagamento;
        this.pagina = pagina;
    }


    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFUncionario) {
        this.idFuncionario = idFUncionario;
    }

    public UUID getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(UUID idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }



}
