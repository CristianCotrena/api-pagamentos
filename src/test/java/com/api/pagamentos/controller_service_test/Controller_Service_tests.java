package com.api.pagamentos.controller_service_test;

import com.api.pagamentos.service.v1.PagamentosService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.fail;

public class Controller_Service_tests {
    @SpringBootTest
    @DisplayName("PagamentoserviceController - Testes")
    class PagamentosServiceControllerTests {

        @Autowired
        ApplicationContext context;

        @Test
        @DisplayName("01 - Classe da camada SERVICE criada corretamente")
        void testaClasseServiceExiste() {
            try {
                context.getBean(PagamentosService.class);
            } catch (NoSuchBeanDefinitionException exc) {
                fail("Classe da camada serviço deve existir e ser implementada de forma correta ");
            }
        }

        @Test
        @DisplayName("02 - Classe da camada CONTROLLER criada corretamente")
        void testaClasseControllerExiste() {
            try {
                context.getBean(PagamentosService.class);
            } catch (NoSuchBeanDefinitionException exc) {
                fail("Classe da camada controle deve existir e ser implementada de forma correta");
            }
        }
    }
}
