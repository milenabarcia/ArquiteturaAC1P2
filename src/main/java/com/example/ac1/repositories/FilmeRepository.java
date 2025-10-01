package com.example.ac1.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ac1.models.filmes;

public interface FilmeRepository 
    extends JpaRepository<filmes, Long> {
        
        List<filmes> findByDuracaoGreaterThan(Integer duracao);

        List<filmes> findByDuracaoLessThanEqual(Integer duracao);

        List<filmes> findByTituloStartingWith(String prefix);



    }