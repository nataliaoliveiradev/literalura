package com.alura.literalura.repositorio;

import com.alura.literalura.titulo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositorioLivros extends JpaRepository<Livro, Long> {

    @Query("SELECT MAX(b.id) FROM Book b")
    Long getMaxId();

    boolean existsById(Long id);

    long countByLanguage(String language);

}