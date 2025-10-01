package com.example.ac1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ac1.models.diretor;
import com.example.ac1.models.filmes;
import com.example.ac1.repositories.DiretorRepository;
import com.example.ac1.repositories.FilmeRepository;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private DiretorRepository diretorRepository;

   
    @GetMapping
    public List<filmes> listar() {
        return filmeRepository.findAll();
    }

    @PostMapping
    public filmes criar(@RequestBody FilmeRequest request) {
        diretor dir = diretorRepository.findById(request.getDiretorId())
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));
        filmes f = new filmes(null, request.getTitulo(), request.getDuracao(), dir);
        return filmeRepository.save(f);
    }

    public static class FilmeRequest {
        private String titulo;
        private Integer duracao;
        private Long diretorId;

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public Integer getDuracao() {
            return duracao;
        }

        public void setDuracao(Integer duracao) {
            this.duracao = duracao;
        }

        public Long getDiretorId() {
            return diretorId;
        }

        public void setDiretorId(Long diretorId) {
            this.diretorId = diretorId;
        }
    }
}
