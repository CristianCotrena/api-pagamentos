package com.api.pagamentos.repository;

import com.api.pagamentos.entity.model.PagamentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PagamentosRepository extends JpaRepository<PagamentosModel, UUID> {

    Optional<Boolean> existsByIdFuncionario(UUID idFuncionario);

    Optional<Boolean> existsByIdFornecedor(UUID idFornecedor);

    Optional<Boolean> existsByStatusPagamento(Object statusPagamento);

    Optional<Boolean> existsByDescricao(String descricao);

    Optional<Boolean> existsByValor(double valor);

    Optional<Boolean> existsByData(ZonedDateTime data);

    Optional<Boolean> existsByStatus(Integer status);
}
