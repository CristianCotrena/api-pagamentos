package com.api.pagamentos.service.v1;

import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.builder.ResponseErrorBuilder;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.constants.MensagemDeErro;
import com.api.pagamentos.dtos.BuscarPagamentoResponseDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.transforme.PagamentosTransformer;
import com.api.pagamentos.validation.BuscarPagamentoValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarPagamentoService {

    @Autowired
    private PagamentosRepository pagamentosRepository;

    public BuscarPagamentoService(PagamentosRepository pagamentosRepository) {
        this.pagamentosRepository = pagamentosRepository;
    }

     public ResponseEntity buscarPagamento(String id){

        List<BaseErrorDto> erros = new BuscarPagamentoValidate().validarParametros(id);
        if (!erros.isEmpty()) {
            ResponseErrorBuilder responseErrorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return responseErrorBuilder.get();
        }

        UUID uuid = null;
        if (id != null && !id.isEmpty()) {
            uuid = UUID.fromString(id);
        }
        BuscarPagamentoResponseDto buscarPagamentoResponseDto = new BuscarPagamentoResponseDto();
        Optional<PagamentosModel> pagamentosModelOptional = pagamentosRepository.findById(uuid);
        if (!pagamentosModelOptional.isPresent()) {
            erros.add(new BaseErrorDto("id", MensagemDeErro.NOT_FOUND));
        } else {
            buscarPagamentoResponseDto = new PagamentosTransformer().transformerBuscarPagamentos(pagamentosModelOptional);
        }
        if (!erros.isEmpty()) {
            ResponseErrorBuilder responseErrorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return responseErrorBuilder.get();
        }

        ResponseSucessBuilder pagamentoEncontrado = new ResponseSucessBuilder<BuscarPagamentoResponseDto>(
                HttpStatus.OK,
                buscarPagamentoResponseDto,
                "Pagamento encontrado."
        );
        return pagamentoEncontrado.get();
     }
}
