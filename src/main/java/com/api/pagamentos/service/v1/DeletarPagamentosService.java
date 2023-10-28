package com.api.pagamentos.service.v1;
import com.api.pagamentos.builder.ResponseErrorBuilder;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.base.dto.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
@Service
public class DeletarPagamentosService {
    private final PagamentosRepository pagamentoRepository;
    @Autowired
    public DeletarPagamentosService(PagamentosRepository pagamentosRepository) {
        this.pagamentoRepository = pagamentosRepository;
    }

    public List<PagamentosModel> listarClientesAtivos() {
        return pagamentoRepository.findByStatus(1);
    }

    public ResponseEntity<? extends BaseDto<? extends Object>> inativarPagamento(UUID id) {
        Optional<PagamentosModel> encontrarCliente = pagamentoRepository.findByid(id);
        if (encontrarCliente.isPresent()) {
            PagamentosModel cliente = encontrarCliente.get();
            cliente.setStatus(0);
            pagamentoRepository.save(cliente);

            return new ResponseSucessBuilder<String>(HttpStatus.OK, "Cliente marcado como inativo com sucesso.").get();
        } else {
            List<BaseErrorDto> errors = new ArrayList<>();
            errors.add(new BaseErrorDto("Cliente", "Cliente não encontrado."));
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
            return resultado.get();
        }
    }
}
