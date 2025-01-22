package com.alura.literalura.service;

import com.alura.literalura.dto.LivroDTO;
import com.alura.literalura.dto.GutendexResponse;
import com.alura.literalura.titulo.Autor;
import com.alura.literalura.titulo.Livro;
import com.alura.literalura.repositorio.RepositorioAutories;
import com.alura.literalura.repositorio.RepositorioLivros;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoLivro {

    private final RepositorioLivros repositorioLivros;
    private final RepositorioAutories repositorioAutories;

    public ServicoLivro(RepositorioLivros repositorioLivros, RepositorioAutories repositorioAutories) {
        this.repositorioLivros = repositorioLivros;
        this.repositorioAutories = repositorioAutories;
    }

    @Transactional
    public void storeBook(GutendexResponse.Book bookResponse) {
        Livro livro = new Livro();
        livro.setTitle(bookResponse.getTitle());

        if (bookResponse.getAuthors() != null && !bookResponse.getAuthors().isEmpty()) {
            List<Autor> autores = bookResponse.getAuthors().stream()
                    .map(author -> new Autor(author.getName(), null, null, livro))
                    .collect(Collectors.toList());
            livro.setAuthors(autores);
        } else {
            livro.setAuthors(new ArrayList<>());
        }

        if (bookResponse.getFormats() != null && !bookResponse.getFormats().isEmpty()) {
            livro.setDownloadLink(bookResponse.getFormats().values().iterator().next());
        }

        if (bookResponse.getLanguages() != null && !bookResponse.getLanguages().isEmpty()) {
            livro.setLanguage(bookResponse.getLanguages().get(0));
        }

        livro.setDownloadCount(1000);

        repositorioLivros.save(livro);
        System.out.println("Livro salvo: " + livro.getTitle());
    }

    @Transactional
    public List<LivroDTO> getAllBooksDTO() {
        List<Livro> livros = repositorioLivros.findAll();
        return livros.stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBookById(Long id) {
        Livro livro = repositorioLivros.findById(id).orElse(null);
        if (livro != null) {
            System.out.println("O livro com ID " + id + " existe. Removendo.");
            repositorioLivros.delete(livro);
            repositorioLivros.flush();
            System.out.println("Livro removido.");
        } else {
            System.out.println("O livro com ID " + id + " não existe.");
        }
    }

    public long countBooksByLanguage(String language) {
        return repositorioLivros.countByLanguage(language);
    }
}
