package com.api.pagamentos.service.v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.base.dto.BaseErrorDto;
import com.api.pagamentos.builder.ResponseErrorBuilder;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.constants.MensagemDeErro;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.transforme.PagamentosTransforme;
import com.api.pagamentos.validation.PagamentosValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentosService {
    private final PagamentosRepository pagamentosRepository;

    public PagamentosService(PagamentosRepository pagamentosRepository) {
        this.pagamentosRepository = pagamentosRepository;
    }

    @Transactional
    public BaseDto cadastrarPagamentos(PagamentosRequestDto pagamentosRequestDto) {
        var erros = new PagamentosValidation().validate(pagamentosRequestDto);

        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.get().getBody();
        }

        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.get().getBody();
        }

        var pagamentos = new PagamentosTransforme().transformarParaPagamentosModel(pagamentosRequestDto);

        var savedPagamentos = pagamentosRepository.save(pagamentos);

        return new ResponseSucessBuilder<PagamentosModel>(HttpStatus.CREATED, savedPagamentos, "Pagamento cadastrado com sucesso.").get().getBody();
    }

    public ResponseEntity buscarPagamento(UUID id){
        Optional<PagamentosModel> encontrarPorId = pagamentosRepository.findById(id);
        if (encontrarPorId.isEmpty()){
            return new ResponseErrorBuilder(HttpStatus.NOT_FOUND,MensagemDeErro.NOT_FOUND).get();
        }
        return new ResponseSucessBuilder(HttpStatus.OK,encontrarPorId).get();
    }
}
