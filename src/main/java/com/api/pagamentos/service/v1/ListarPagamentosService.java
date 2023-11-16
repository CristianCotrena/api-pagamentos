package com.api.pagamentos.service.v1;

import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.builder.ResponseErrorBuilder;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.dtos.ListarPagamentosRequestDto;
import com.api.pagamentos.dtos.ListarPagamentosResponseDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.dtos.PaginamentoListaResponseDto;
import com.api.pagamentos.validation.ListarPagamentosValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPagamentosService {

    private final PagamentosRepository pagamentoRepository;

    @Autowired
    public ListarPagamentosService(PagamentosRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public ResponseEntity listarPagamentos(ListarPagamentosRequestDto request) {
        List<BaseErrorDto> errors = new ListarPagamentosValidation().validate(request);
        if(errors.size() > 0){
            return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors).get();
        }
        Page<PagamentosModel> pagamentos = filtrarPagamentos(request);

        List<ListarPagamentosResponseDto> pagamentoResponseList = pagamentos.getContent().stream()
                .map(pagamento -> new ListarPagamentosResponseDto(
                        pagamento.getIdFuncionario(),
                        pagamento.getIdFornecedor(),
                        pagamento.getIdCliente(),
                        pagamento.getStatusPagamento(),
                        pagamento.getDescricao(),
                        pagamento.getValor(),
                        pagamento.getData()
                )).collect(Collectors.toList());

        PaginamentoListaResponseDto paginaListagemDto = new PaginamentoListaResponseDto(pagamentoResponseList, request.getPagina(), pagamentos.getTotalPages());
        return new ResponseSucessBuilder<PaginamentoListaResponseDto>(HttpStatus.OK,paginaListagemDto).get();
    }

    private Page<PagamentosModel> filtrarPagamentos(ListarPagamentosRequestDto request) {
        Specification<PagamentosModel> specification = Specification.where(null);

        if (request.getIdCliente()!= null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("idCliente"), request.getIdCliente()));
        }

        if (request.getIdFuncionario() != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("idFuncionario"), request.getIdFuncionario()));
        }

        if (request.getIdFornecedor() != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("idFornecedor"), request.getIdFornecedor()));
        }

        if (request.getStatusPagamento() != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("statusPagamento"), request.getStatusPagamento()));
        }

        int pageSize = 10;
        Pageable pageable = PageRequest.of(request.getPagina(), pageSize, Sort.by(Sort.Order.asc("data")));

        return pagamentoRepository.findAll(specification, pageable);
    }
}
