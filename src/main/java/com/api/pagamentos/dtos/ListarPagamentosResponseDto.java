package com.api.pagamentos.dtos;
import com.api.pagamentos.entity.model.PagamentoEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
public class ListarPagamentosResponseDto implements serializable {
    private static final long serialVersionUID = 1L;
    private UUID idCliente;
    private UUID idFuncionario;
    private UUID idFornecedor;
    private PagamentoEnum statusPagamento
    private String descricao;
    private double valor;
    private ZonedDateTime data;

    public ListarPagamentosResponseDto(UUID idCliente,UUID idFuncionario, UUID idFornecedor, PagamentoEnum statusPagamento,String descricao, double valor, ZonedDateTime data){
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.statusPagamento = statusPagamento;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
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

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public UUID getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(UUID idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public PagamentoEnum getStatusPamento() {
        return statusPamento;
    }

    public void setStatusPamento(PagamentoEnum statusPamento) {
        this.statusPamento = statusPamento;
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
}
