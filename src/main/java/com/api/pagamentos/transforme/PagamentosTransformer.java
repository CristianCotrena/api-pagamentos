package com.api.pagamentos.transforme;

import com.api.pagamentos.dtos.BuscarPagamentoResponseDto;
import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.entity.model.PagamentosModel;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
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

    public BuscarPagamentoResponseDto transformerBuscarPagamentos(Optional<PagamentosModel> pagamentosModelOptional) {

        BuscarPagamentoResponseDto buscarPagamentoResponseDto = new BuscarPagamentoResponseDto();
        buscarPagamentoResponseDto.setId(pagamentosModelOptional.get().getId());
        buscarPagamentoResponseDto.setIdCliente(pagamentosModelOptional.get().getIdCliente());
        buscarPagamentoResponseDto.setIdFornecedor(pagamentosModelOptional.get().getIdFornecedor());
        buscarPagamentoResponseDto.setIdFuncionario(pagamentosModelOptional.get().getIdFuncionario());
        buscarPagamentoResponseDto.setStatusPagamento(pagamentosModelOptional.get().getStatusPagamento().toString());
        buscarPagamentoResponseDto.setDescricao(pagamentosModelOptional.get().getDescricao());

        String valorString = String.valueOf(pagamentosModelOptional.get().getValor());
        BigDecimal valorBigDecimal = new BigDecimal(valorString).setScale(2, BigDecimal.ROUND_UP);
        buscarPagamentoResponseDto.setValor(Double.parseDouble(String.valueOf(valorBigDecimal)));

        buscarPagamentoResponseDto.setData(pagamentosModelOptional.get().getData());
        return buscarPagamentoResponseDto;
    }
}
