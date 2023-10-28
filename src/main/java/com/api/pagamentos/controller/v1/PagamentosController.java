package com.api.pagamentos.controller.v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.service.v1.DeletarPagamentosService;
import com.api.pagamentos.service.v1.PagamentosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/pagamentos")
@Tag(
        name = "API Pagamentos",
        description = "Microserviço para API Pagamentos Forma NT - Academia"
)
public class PagamentosController {
    @Autowired
    private PagamentosService pagamentosService;


    @Operation(
            summary = "Criar pagamentos",
            description = "Cria um novo pagamento",
            method = "POST"
    )
    @ApiResponse(
            responseCode = "201",
            description = "pagamento criado com sucesso")
    @ApiResponse(
            responseCode = "400",
            description = "Erro de validação")
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno")

    @PostMapping("/cadastrar")
    public BaseDto cadastrarPagamentos(@RequestBody PagamentosRequestDto pagamentosRequestDto) {
        return pagamentosService.cadastrarPagamentos(pagamentosRequestDto);
    }
    @Operation(summary = "Busca registro de um pagamento ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseDto> buscarUmPagamento(@PathVariable(value = "id") UUID id) {
        return pagamentosService.buscarPagamento(id);
    }
    @Autowired
    private DeletarPagamentosService deletarPagamentosService;
    //delete
    @DeleteMapping("/inativar/{id}")
    @Operation(summary = "Inativar pagamento", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pagamento inativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
    })
    public ResponseEntity<BaseDto<Void>> inativarPagamento(@PathVariable(value = "id") UUID id) {
        ResponseEntity<? extends BaseDto<? extends Object>> result = deletarPagamentosService.inativarPagamento(id);
        if (result.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.noContent().build();
        } else if (result.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

