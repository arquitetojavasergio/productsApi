package br.com.cotiinformatica.components;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@Component
public class LoadDataComponent implements ApplicationRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Criação das categorias usando construtor sem argumentos
        Categoria informatica = new Categoria();
        informatica.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        informatica.setNome("INFORMÁTICA");

        Categoria eletronicos = new Categoria();
        eletronicos.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        eletronicos.setNome("ELETRÔNICOS");

        Categoria moveis = new Categoria();
        moveis.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"));
        moveis.setNome("MÓVEIS");

        Categoria alimentos = new Categoria();
        alimentos.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174003"));
        alimentos.setNome("ALIMENTOS");

        Categoria roupas = new Categoria();
        roupas.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174004"));
        roupas.setNome("ROUPAS");

        Categoria livros = new Categoria();
        livros.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174005"));
        livros.setNome("LIVROS");

        Categoria brinquedos = new Categoria();
        brinquedos.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174006"));
        brinquedos.setNome("BRINQUEDOS");

        categoriaRepository.save(informatica);
        categoriaRepository.save(eletronicos);
        categoriaRepository.save(moveis);
        categoriaRepository.save(alimentos);
        categoriaRepository.save(roupas);
        categoriaRepository.save(livros);
        categoriaRepository.save(brinquedos);
    }
}
