package com.api.pagamentos.controller.v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.service.v1.CadastrarPagamentosService;
import com.api.pagamentos.dtos.ListarPagamentosRequestDto;
import com.api.pagamentos.service.v1.ListarPagamentosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ListarPagamentosService listarPagamentosService;
    private final CadastrarPagamentosService cadastrarPagamentosService;
    
    @Autowired
    public PagamentosController(ListarPagamentosService listarPagamentosService, CadastrarPagamentosService cadastrarPagamentosService) {
        this.listarPagamentosService = listarPagamentosService;
        this.cadastrarPagamentosService = cadastrarPagamentosService;
    }

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
    public ResponseEntity<BaseDto<PagamentosModel>> cadastrarPagamentos(@RequestBody CadastrarPagamentoRequestDto cadastrarPagamentoRequestDto) {
        ResponseEntity<BaseDto<PagamentosModel>> resultado = cadastrarPagamentosService.cadastrarPagamentos(cadastrarPagamentoRequestDto);
        return resultado;
    }

    /**
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
    **/
    @Operation(
            summary = "Listar pagamentos",
            description = "Lista todos pagamentos, por id",
            method = "GET"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Lista feita com sucesso")
    @ApiResponse(
            responseCode = "400",
            description = "Erro de requisitos")
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno")
    @GetMapping("/v1/pagamentos")
    public ResponseEntity listarPagamentos(
            @RequestParam(required = false) UUID idCliente,
            @RequestParam(required = false) UUID idFuncionario,
            @RequestParam(required = false) UUID idFornecedor,
            @RequestParam(required = false) String statusPagamento,
            @RequestParam(required = false, defaultValue = "1") int page) {
        return listarPagamentosService.listarPagamentos(new ListarPagamentosRequestDto(idCliente, idFuncionario, idFornecedor, statusPagamento, page));
    }
}

