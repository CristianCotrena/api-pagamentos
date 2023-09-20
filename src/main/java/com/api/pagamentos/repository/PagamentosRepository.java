package com.api.pagamentos.repository;
import com.api.pagamentos.model.PagamentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface PagamentosRepository extends JpaRepository<PagamentosModel, UUID> {

}
