package com.example.ac1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ac1.models.diretor;
import com.example.ac1.repositories.DiretorRepository;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorRepository diretorRepository;

    // GET /diretores → lista todos
    @GetMapping
    public List<diretor> listar() {
        return diretorRepository.findAll();
    }

    // POST /diretores → cria um novo
    @PostMapping
    public diretor criar(@RequestBody diretor d) {
        return diretorRepository.save(d);
    }
}