package com.api.pagamentos.transforme;

import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        String formatoDaData = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatoDaData);
        try {
            pagamentosModel.setData(simpleDateFormat.parse(dto.getData()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        pagamentosModel.setValor(Double.parseDouble(dto.getValor()));
        pagamentosModel.setStatus(1);

        return pagamentosModel;
    }
}
