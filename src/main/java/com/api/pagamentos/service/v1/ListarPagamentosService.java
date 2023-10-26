package com.api.pagamentos.service.v1;

import com.api.pagamentos.dtos.ListarPagamentosResponseDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.dtos.paginamentoListaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPagamentosService {

    private final PagamentosRepository pagamentoRepository;

    @Autowired
    public ListarPagamentosService(PagamentosRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public ResponseEntity listarPagamentos(
            UUID idCliente,
            UUID idFuncionario,
            UUID idFornecedor,
            PagamentoEnum statusPagamento,
            int pagina) {


        Page<PagamentosModel> pagamentos = filtrarPagamentos(idCliente, idFuncionario, idFornecedor, statusPagamento, pagina);
        if (idCliente == null && idFuncionario == null && idFornecedor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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

        paginamentoListaResponseDto paginaListagemDto = new paginamentoListaResponseDto(pagamentoResponseList, pagina, pagamentos.getTotalPages());
        return new ResponseEntity<>(paginaListagemDto, HttpStatus.OK);
    }

    private Page<PagamentosModel> filtrarPagamentos(UUID idCliente, UUID idFuncionario, UUID idFornecedor, PagamentoEnum statusPagamento, int pagina) {
        Specification<PagamentosModel> specification = Specification.where(null);

        if (idCliente != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("idCliente"), idCliente));
        }

        if (idFuncionario != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("idFuncionario"), idFuncionario));
        }

        if (idFornecedor != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("idFornecedor"), idFornecedor));
        }

        if (statusPagamento != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("statusPagamento"), statusPagamento));
        }

        int pageSize = 10;
        Pageable pageable = PageRequest.of(pagina, pageSize, Sort.by(Sort.Order.asc("data")));

        return pagamentoRepository.findAll(specification, pageable);
    }
}
