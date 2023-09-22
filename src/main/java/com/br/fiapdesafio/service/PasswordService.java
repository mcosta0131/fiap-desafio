package com.br.fiapdesafio.service;

import com.br.fiapdesafio.client.PassClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordService {

    @Autowired
    PassClient client;

    public String calcularSHA1(String texto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = md.digest(texto.getBytes());
        StringBuilder hexHash = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexHash.append('0');
            }
            hexHash.append(hex);
        }
        return hexHash.toString().toUpperCase();
    }

    public String isLeaked(String hash, String sha) {
        List<String> lista = converterStringParaLista(client.validadePass(hash), hash);
        lista.forEach(elemento -> {
            if (elemento.equals(sha.toUpperCase())) {
                System.out.println("Elemento da lista " + elemento + " HASH : " + sha);
            }
        });
        if (lista.contains(sha.toUpperCase())) {
            return "Senha comprometida";
        }
        return "Senha segura";
    }

    public List<String> converterStringParaLista(String texto, String hash) {
        // Dividir a string em linhas
        String[] linhas = texto.split("\n");

        // Criar uma lista para armazenar os elementos
        List<String> lista = new ArrayList<>();

        for (String linha : linhas) {
            // Dividir a linha pelo ":" e pegar a parte antes do ":"
            String[] partes = linha.split(":");
            if (partes.length > 0) {
                lista.add(hash.toUpperCase().concat(partes[0]).toUpperCase());
            }
        }

        return lista;
    }
}
