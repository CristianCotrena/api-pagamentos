package com.api.pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPagamentosApplication.class, args);
		System.out.println("Hello word");
	}
	@GetMapping("/helloword")
	public String helloWord(){
		return "Hello word da api-pagamentos";
	}
}
