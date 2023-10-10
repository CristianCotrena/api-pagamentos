package com.api.pagamentos.controller.v1;

import com.api.pagamentos.base.dto.BaseDto;
import com.api.pagamentos.dtos.PagamentosRequestDto;
import com.api.pagamentos.service.v1.PagamentosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class PagamentosController {
    @RestController
    @RequestMapping("/v1/pagamentos")
    @Tag(
            name = "API Pagamentos",
            description = "Microserviço para API Pagamentos Forma NT - Academia"
    )
    public class DadosBancariosController {
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
    }
}
