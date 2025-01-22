package com.alura.literalura.repositorio;

import com.alura.literalura.titulo.Autor;
import com.alura.literalura.titulo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioAutories extends JpaRepository<Autor, Long> {
    List<Autor> findByBook(Livro livro);
    @Query("SELECT a FROM Author a WHERE (a.birth_year IS NOT NULL AND a.birth_year <= :year) " +
            "AND (a.death_year IS NULL OR a.death_year > :year)")
    List<Autor> findAuthorsAliveInYear(@Param("year") int year);

}