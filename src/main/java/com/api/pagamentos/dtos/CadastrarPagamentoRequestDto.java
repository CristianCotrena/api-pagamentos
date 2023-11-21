package com.api.pagamentos.dtos;

import com.api.pagamentos.entity.model.PagamentoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class CadastrarPagamentoRequestDto {
    @Schema(description = "Id do funcionário", example = "546546556")
    private String idFuncionario;
    @Schema(description = "Id do fornecedor", example = "546546556")
    private String idFornecedor;
    @Schema(description = "Id do cliente", example = "546546556")
    private String idCliente;
    @Schema(description = "pagamento", example = "Pagar/receber")
    private String statusPagamento;
    @Schema(description = "pagamento", example = "pago/pendente")
    private String descricao;
    @Schema(description = "Valor a ser pago", example = "150,00")
    private String valor;
    @Schema(description = "Data do pagamento", example = "2023/12/20")
    private String data;
    @Schema(description = "Status da conta", example = "1")
    private Integer status;

    public CadastrarPagamentoRequestDto(
            String idFuncionario,
            String idFornecedor,
            String idCliente,
            String statusPagamento,
            String descricao,
            String valor,
            String data,
            Integer status) {
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.idCliente = idCliente;
        this.statusPagamento = statusPagamento;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.status = status;
    }

    public CadastrarPagamentoRequestDto() {
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
