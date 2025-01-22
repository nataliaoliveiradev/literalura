package com.alura.literalura.service;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.titulo.Autor;
import com.alura.literalura.titulo.Livro;
import com.alura.literalura.repositorio.RepositorioAutories;
import com.alura.literalura.repositorio.RepositorioLivros;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoAutor {

    private final RepositorioAutories repositorioAutories;
    private final RepositorioLivros repositorioLivros;

    public ServicoAutor(RepositorioAutories repositorioAutories, RepositorioLivros repositorioLivros) {
        this.repositorioAutories = repositorioAutories;
        this.repositorioLivros = repositorioLivros;
    }

    @Transactional
    public Autor createAuthor(String name, Long bookId) {
        Livro livro = repositorioLivros.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("ID do livro não encontrado: " + bookId));
        Autor autor = new Autor(name, null, null, livro);
        return repositorioAutories.save(autor);
    }

    @Transactional
    public List<AutorDTO> getAllAuthors() {
        List<Autor> autores = repositorioAutories.findAll();
        return autores.stream()
                .map(AutorDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<AutorDTO> getAuthorsByBookId(Long bookId) {
        Livro livro = repositorioLivros.findById(bookId).orElseThrow(() -> new IllegalArgumentException("ID do livro não encontrado: " + bookId));
        List<Autor> autores = repositorioAutories.findByBook(livro);
        return autores.stream()
                .map(AutorDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAuthorById(Long id) {
        Autor autor = repositorioAutories.findById(id).orElseThrow(() -> new IllegalArgumentException("ID do autor não encontrado: " + id));
        repositorioAutories.delete(autor);
    }

    public List<AutorDTO> getAuthorsAliveInYear(int year) {
        List<Autor> autores = repositorioAutories.findAuthorsAliveInYear(year);
        return autores.stream()
                .map(AutorDTO::new)
                .toList();
    }

    public AutorDTO convertToDTO(Autor autor) {
        Integer birthYear = autor.getBirth_year() != null ? autor.getBirth_year() : -1;
        Integer deathYear = autor.getDeath_year() != null ? autor.getDeath_year() : -1;

        AutorDTO autorDTO = new AutorDTO(autor);
        autorDTO.setBirth_year(birthYear);
        autorDTO.setDeath_year(deathYear);

        return autorDTO;
    }
}
