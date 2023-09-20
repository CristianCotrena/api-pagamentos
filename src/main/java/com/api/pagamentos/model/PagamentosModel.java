package com.api.pagamentos.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

    @Entity
    @Table(name = "pagamentos")
    public class PagamentosModel implements Serializable {
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        @Column(nullable = false, unique = true)
        private UUID id_cliente;
        @Column(nullable = false, unique = true)
        private UUID id_funcionario;
        @Column(nullable = false, unique = true)
        private UUID id_fornecedor;
        @Column(nullable = false)
        private StatusPagamento statusPagamento;
        @Column(nullable = false)
        private String descricao;
        @Column(nullable = false)
        private double valor;
        @Column(nullable = false)
        private Date data;
        @Column(nullable = false, columnDefinition = "int default 1")
        private int status;

        public PagamentosModel(UUID id, UUID id_cliente, UUID id_funcionario, UUID id_fornecedor, StatusPagamento statusPagamento, String descricao, double valor, Date data, int status) {
            this.id = id;
            this.id_cliente = id_cliente;
            this.id_funcionario = id_funcionario;
            this.id_fornecedor = id_fornecedor;
            this.statusPagamento = statusPagamento;
            this.descricao = descricao;
            this.valor = valor;
            this.data = data;
            this.status = status;
        }

        public PagamentosModel() {
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public UUID getId_cliente() {
            return id_cliente;
        }

        public void setId_cliente(UUID id_cliente) {
            this.id_cliente = id_cliente;
        }

        public UUID getId_funcionario() {
            return id_funcionario;
        }

        public void setId_funcionario(UUID id_funcionario) {
            this.id_funcionario = id_funcionario;
        }

        public UUID getId_fornecedor() {
            return id_fornecedor;
        }

        public void setId_fornecedor(UUID id_fornecedor) {
            this.id_fornecedor = id_fornecedor;
        }

        public StatusPagamento getStatusPagamento() {
            return statusPagamento;
        }

        public void setStatusPagamento(StatusPagamento statusPagamento) {
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

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
