package com.api.pagamentos.transforme;

import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;

import java.util.UUID;

public class PagamentosTransformer {

    public PagamentosModel transformarParaPagamentosModel(CadastrarPagamentoRequestDto dto) {
        PagamentosModel pagamentosModel = new PagamentosModel();
        if (dto.getIdCliente() != null && !dto.getIdCliente().isEmpty()) {
            pagamentosModel.setIdCliente(UUID.fromString(dto.getIdCliente()));
        }
        if (dto.getIdFornecedor() != null && !dto.getIdFornecedor().isEmpty()) {
            pagamentosModel.setIdFornecedor(UUID.fromString(dto.getIdFornecedor()));
        }
        if (dto.getIdFuncionario() != null && !dto.getIdFuncionario().isEmpty()) {
            pagamentosModel.setIdFuncionario(UUID.fromString(dto.getIdFuncionario()));
        }

        pagamentosModel.setStatusPagamento(PagamentoEnum.statusEscolhido(dto.getStatusPagamento()));
        pagamentosModel.setDescricao(dto.getDescricao());
        pagamentosModel.setData(dto.getData());
        pagamentosModel.setValor(Double.parseDouble(dto.getValor()));
        pagamentosModel.setStatus(1);

        return pagamentosModel;
    }
}
