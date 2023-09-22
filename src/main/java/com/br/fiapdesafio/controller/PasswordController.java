package com.br.fiapdesafio.controller;

import com.br.fiapdesafio.service.PasswordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping(value = "/pass")
@Tag(name = "PasswordController", description = "Identifica se a sua senha foi comprometida")
public class PasswordController {

    @Autowired
    private PasswordService service;

    @GetMapping()
    public String validatePass(@RequestParam String password) throws NoSuchAlgorithmException {
        String sha1 = service.calcularSHA1(password);
        return service.isLeaked(sha1.substring(0, 5), sha1);
    }
}
