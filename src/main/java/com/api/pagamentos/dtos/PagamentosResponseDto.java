package com.api.pagamentos.dtos;

import com.api.pagamentos.entity.model.PagamentoEnum;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

public class PagamentosResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID idFuncionario;
    private UUID idFornecedor;
    private UUID idCliente;
    private PagamentoEnum pagamentoEnum;
    private String descricao;
    private double valor;
    private ZonedDateTime data;
    private Integer status;

    public PagamentosResponseDto(UUID idFuncionario, UUID idFornecedor, UUID idCliente, PagamentoEnum pagamentoEnum, String descricao, double valor, ZonedDateTime data, Integer status) {
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.idCliente = idCliente;
        this.pagamentoEnum = pagamentoEnum;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.status = status;
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public UUID getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(UUID idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public PagamentoEnum getStatusPagamento() {
        return pagamentoEnum;
    }

    public void setStatusPagamento(PagamentoEnum pagamentoEnum) {
        this.pagamentoEnum = pagamentoEnum;
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

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
