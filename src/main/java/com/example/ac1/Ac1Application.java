package com.example.ac1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ac1.repositories.DiretorRepository;
import com.example.ac1.repositories.FilmeRepository;
import com.example.ac1.models.diretor;
import com.example.ac1.models.filmes;

import java.util.Arrays;

@SpringBootApplication
public class Ac1Application implements CommandLineRunner {

    private final FilmeRepository filmeRepository;
    private final DiretorRepository diretorRepository;

    public Ac1Application(FilmeRepository filmeRepository, DiretorRepository diretorRepository) {
        this.filmeRepository = filmeRepository;
        this.diretorRepository = diretorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Ac1Application.class, args);
    }

    @Override
    public void run(String... args) {

        // Criando diretores
        diretor d1 = new diretor(null, "Christopher Nolan", null);
        diretor d2 = new diretor(null, "Quentin Tarantino", null);

        // Criando filmes
        filmes f1 = new filmes(null, "Inception", 148, d1);
        filmes f2 = new filmes(null, "Interstellar", 169, d1);
        filmes f3 = new filmes(null, "Pulp Fiction", 154, d2);
        filmes f4 = new filmes(null, "Django Unchained", 165, d2);

        // Relacionando filmes aos diretores
        d1.setFilmes(Arrays.asList(f1, f2));
        d2.setFilmes(Arrays.asList(f3, f4));

        // Salvando diretores (e os filmes em cascata)
        diretorRepository.saveAll(Arrays.asList(d1, d2));

        // ---------------- Testando FilmeRepository ----------------
        System.out.println("\n=== Filmes com duração > 150 minutos ===");
        filmeRepository.findByDuracaoGreaterThan(150)
                .forEach(f -> System.out.println(f.getTitulo() + " - " + f.getDuracao()));

        System.out.println("\n=== Filmes com duração <= 150 minutos ===");
        filmeRepository.findByDuracaoLessThanEqual(150)
                .forEach(f -> System.out.println(f.getTitulo() + " - " + f.getDuracao()));

        System.out.println("\n=== Filmes cujo título começa com 'In' ===");
        filmeRepository.findByTituloStartingWith("In")
                .forEach(f -> System.out.println(f.getTitulo()));

        // ---------------- Testando DiretorRepository ----------------
        System.out.println("\n=== Diretores cujo nome começa com 'C' ===");
        diretorRepository.findByNomeStartingWith("C")
                .forEach(d -> System.out.println(d.getNome()));

        System.out.println("\n=== Buscando diretor com filmes (ID=1) ===");
        diretor dirComFilmes = diretorRepository.findDiretorFetchFilmes(1L);
        if (dirComFilmes != null) {
            System.out.println("Diretor: " + dirComFilmes.getNome());
            dirComFilmes.getFilmes()
                    .forEach(f -> System.out.println(" - " + f.getTitulo()));
        }
    }
}
