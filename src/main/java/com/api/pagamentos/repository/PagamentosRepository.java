package com.api.pagamentos.repository;

import com.api.pagamentos.entity.model.PagamentosModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PagamentosRepository extends JpaRepository<PagamentosModel, UUID>, JpaSpecificationExecutor<PagamentosModel> {
    Optional<Boolean> existsByStatusPagamento(Object statusPagamento);

    Optional<Boolean> existsByDescricao(String descricao);

    Optional<Boolean> existsByValor(double valor);

    Optional<Boolean> existsByStatus(Integer status);



}
