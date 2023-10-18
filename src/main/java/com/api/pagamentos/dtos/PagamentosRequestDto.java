package com.api.pagamentos.dtos;

import com.api.pagamentos.entity.model.PagamentoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import java.util.UUID;

public class PagamentosRequestDto {
    @Schema(description = "Id do funcionário", example = "546546556")
    private UUID idFuncionario;
    @Schema(description = "Id do fornecedor", example = "546546556")
    private UUID idFornecedor;
    @Schema(description = "Id do cliente", example = "546546556")
    private UUID idCliente;
    @Schema(description = "pagamento", example = "Pagar/receber")
    private PagamentoEnum pagamentoEnum;
    @Schema(description = "pagamento", example = "pago/pendente")
    private String descricao;
    @Schema(description = "Valor a ser pago", example = "150,00")
    private double valor;
    @Schema(description = "Data do pagamento", example = "2023/12/20")
    private ZonedDateTime data;
    @Schema(description = "Status da conta", example = "1")
    private Integer status;

    public PagamentosRequestDto(UUID idFuncionario, UUID idFornecedor, UUID idCliente, PagamentoEnum pagamentoEnum, String descricao, double valor, ZonedDateTime data, Integer status) {
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.idCliente = idCliente;
        this.pagamentoEnum = pagamentoEnum;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.status = status;
    }

    public PagamentosRequestDto() {
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID uuid) {
        this.idFuncionario = uuid;
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
