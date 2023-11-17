package com.api.pagamentos.controller.v1;

import com.api.pagamentos.base.dto.BaseDto;
<<<<<<< HEAD
import com.api.pagamentos.dtos.CadastrarPagamentoRequestDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.service.v1.BuscarPagamentoService;
import com.api.pagamentos.service.v1.CadastrarPagamentosService;
import com.fasterxml.jackson.databind.ser.Serializers;
=======
import com.api.pagamentos.dtos.ListarPagamentosRequestDto;
import com.api.pagamentos.dtos.ListarPagamentosResponseDto;
import com.api.pagamentos.entity.model.PagamentoEnum;
import com.api.pagamentos.repository.PagamentosRepository;
import com.api.pagamentos.service.v1.ListarPagamentosService;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.service.v1.PagamentosService;
>>>>>>> fa02f2c4479a222690dab2c48bb329428e76edc6
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import java.util.List;
import java.util.UUID;

>>>>>>> fa02f2c4479a222690dab2c48bb329428e76edc6
@RestController
@RequestMapping("/v1/pagamentos")
@Tag(
        name = "API Pagamentos",
        description = "Microserviço para API Pagamentos Forma NT - Academia"
)
public class PagamentosController {
<<<<<<< HEAD

    @Autowired
    private CadastrarPagamentosService cadastrarPagamentosService;
    private BuscarPagamentoService buscarPagamentoService;

    public PagamentosController(
            CadastrarPagamentosService cadastrarPagamentosService,
            BuscarPagamentoService buscarPagamentoService) {
        this.cadastrarPagamentosService = cadastrarPagamentosService;
        this.buscarPagamentoService = buscarPagamentoService;
=======
    private final ListarPagamentosService listarPagamentosService;
    private final PagamentosRepository pagamentoRepository;
    @Autowired
    private PagamentosService pagamentosService;
    @Autowired
    public PagamentosController(ListarPagamentosService listarPagamentosService, PagamentosRepository pagamentoRepository) {
        this.listarPagamentosService = listarPagamentosService;
        this.pagamentoRepository = pagamentoRepository;
>>>>>>> fa02f2c4479a222690dab2c48bb329428e76edc6
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
     **/
    @GetMapping("/{id}")
    public ResponseEntity<BaseDto<PagamentosModel>> buscarUmPagamento(@PathVariable(value = "id") String id) {
        ResponseEntity<BaseDto<PagamentosModel>> resultado = buscarPagamentoService.buscarPagamento(id);
        return resultado;
    }
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

