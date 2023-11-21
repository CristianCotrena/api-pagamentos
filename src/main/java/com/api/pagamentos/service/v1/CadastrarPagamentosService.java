package com.api.pagamentos.service.v1;

import com.api.pagamentos.builder.ResponseErrorBuilder;
import com.api.pagamentos.builder.ResponseSucessBuilder;
import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.dtos.CadastrarPagamentoResponseDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.transforme.PagamentosTransformer;
import com.api.pagamentos.validation.CadastrarPagamentosValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CadastrarPagamentosService {

    private PagamentosRepository pagamentosRepository;

    public CadastrarPagamentosService(PagamentosRepository pagamentosRepository) {
        this.pagamentosRepository = pagamentosRepository;
    }

    @Transactional
    public ResponseEntity cadastrarPagamentos(CadastrarPagamentoRequestDto cadastrarPagamentoRequestDto) {
        var erros = new CadastrarPagamentosValidation().validate(cadastrarPagamentoRequestDto);

        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.get();
        }

        PagamentosModel pagamentos = new PagamentosTransformer().transformarParaPagamentosModel(cadastrarPagamentoRequestDto);
        UUID savedPagamentos = pagamentosRepository.save(pagamentos).getId();

        ResponseSucessBuilder cadastradoComSucesso = new ResponseSucessBuilder<CadastrarPagamentoResponseDto>(
                HttpStatus.CREATED,
                new CadastrarPagamentoResponseDto(savedPagamentos.toString()),
                "Pagamento cadastrado com sucesso."
        );
        return cadastradoComSucesso.get();
    }

    /**
    public ResponseEntity buscarPagamento(UUID id){
        Optional<PagamentosModel> encontrarPorId = pagamentosRepository.findById(id);
        if (encontrarPorId.isEmpty()){
            return new ResponseErrorBuilder(HttpStatus.NOT_FOUND,MensagemDeErro.NOT_FOUND).get();
        }
        return new ResponseSucessBuilder(HttpStatus.OK,encontrarPorId).get();
    }
     **/
}
