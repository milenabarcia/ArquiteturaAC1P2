package com.example.ac1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ac1.models.diretor;

public interface DiretorRepository
        extends JpaRepository<diretor, Long> {

    List<diretor> findByNomeStartingWith(String prefix);

@Query("SELECT d FROM diretor d LEFT JOIN FETCH d.filmes WHERE d.id = :id")
diretor findDiretorFetchFilmes(@Param("id") Long id);

}