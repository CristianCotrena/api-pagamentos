package com.api.pagamentos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@OpenAPIDefinition(info = @Info(title = "Pagamentos", version = "1", description = "Microserviço para API Pagamentos Forma NT - Academia"))
public class ApiPagamentosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPagamentosApplication.class, args);
    }

    @GetMapping("/")
    public String helloWord() {
        return "Hello word da api-pagamentos";
    }
}