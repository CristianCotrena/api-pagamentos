package com.api.pagamentos.dtos;

import java.util.UUID;

public class BuscarPagamentoResponseDto {

    private UUID id;
    private UUID idCliente;
    private UUID idFornecedor;
    private UUID idFuncionario;
    private String statusPagamento;
    private String descricao;
    private double valor;
    private String data;

    public BuscarPagamentoResponseDto() {
    }

    public BuscarPagamentoResponseDto(
            UUID id,
            UUID idCliente,
            UUID idFornecedor,
            UUID idFuncionario,
            String statusPagamento,
            String descricao,
            double valor,
            String data) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFornecedor = idFornecedor;
        this.idFuncionario = idFuncionario;
        this.statusPagamento = statusPagamento;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public UUID getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(UUID idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
