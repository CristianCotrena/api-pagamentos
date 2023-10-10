package com.api.pagamentos.transforme;

import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentosModel;

public class PagamentosTransforme {
    public PagamentosModel transformarParaPagamentosModel(PagamentosRequestDto dto) {
        PagamentosModel pagamentosModel = new PagamentosModel();
        pagamentosModel.setIdFuncionario(dto.getIdFuncionario());
        pagamentosModel.setIdFornecedor(dto.getIdFornecedor());
        pagamentosModel.setIdCliente(dto.getIdCliente());
        pagamentosModel.setStatusPagamento(dto.getStatusPagamento());
        pagamentosModel.setDescricao(dto.getDescricao());
        pagamentosModel.setData(dto.getData());

        if (dto.getStatus() != null) {
            pagamentosModel.setStatus(dto.getStatus());
        } else {
            pagamentosModel.setStatus(1);
        }
        return pagamentosModel;
    }
}
