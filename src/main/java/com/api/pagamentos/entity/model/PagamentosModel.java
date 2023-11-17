package com.api.pagamentos.entity.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

    @Entity
    @Table(name = "pagamentos")
    public class PagamentosModel implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        @Column
        private UUID idCliente;
        @Column
        private UUID idFuncionario;
        @Column
        private UUID idFornecedor;
        @Column(nullable = false)
        private PagamentoEnum statusPagamento;
        @Column(nullable = false)
        private String descricao;
        @Column(nullable = false)
        private double valor;
        @Column(nullable = false)
        private String data;
        @Column(nullable = false, columnDefinition = "int default 1")
        private int status;

<<<<<<< HEAD
        public PagamentosModel() {
        }

        public PagamentosModel(
                UUID id,
                UUID idCliente,
                UUID idFuncionario,
                UUID idFornecedor,
                PagamentoEnum statusPagamento,
                String descricao,
                double valor,
                String data,
                int status) {
            this.id = id;
            this.idCliente = idCliente;
            this.idFuncionario = idFuncionario;
            this.idFornecedor = idFornecedor;
            this.statusPagamento = statusPagamento;
            this.descricao = descricao;
            this.valor = valor;
            this.data = data;
            this.status = status;
=======
        public PagamentosModel(UUID idFuncionario, Object o, Object o1, PagamentoEnum pagamentoEnum, String pagamentoPendente, double v, Object o2) {
>>>>>>> fa02f2c4479a222690dab2c48bb329428e76edc6
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

        public PagamentoEnum getStatusPagamento() {
            return statusPagamento;
        }

        public void setStatusPagamento(PagamentoEnum statusPagamento) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }