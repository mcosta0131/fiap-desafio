package com.br.fiapdesafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FiapDesafioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiapDesafioApplication.class, args);
    }

}
