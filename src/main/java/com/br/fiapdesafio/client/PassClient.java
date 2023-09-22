package com.br.fiapdesafio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "pwned", url = "https://api.pwnedpasswords.com")
public interface PassClient {
        @GetMapping("/range/{hash}")
        String validadePass(@PathVariable String hash);
}
