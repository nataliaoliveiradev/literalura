package com.alura.literalura.service;

import com.alura.literalura.dto.GutendexResponse;
import com.alura.literalura.titulo.Autor;
import com.alura.literalura.titulo.Livro;
import com.alura.literalura.repositorio.RepositorioAutories;
import com.alura.literalura.repositorio.RepositorioLivros;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final RepositorioLivros repositorioLivros;
    private final RepositorioAutories repositorioAutories;

    public GutendexService(RestTemplate restTemplate, ObjectMapper objectMapper, RepositorioLivros repositorioLivros, RepositorioAutories repositorioAutories) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.repositorioLivros = repositorioLivros;
        this.repositorioAutories = repositorioAutories;
    }

    public List<GutendexResponse.Book> searchBooksByTitle(String title) {
        String apiUrl = "https://gutendex.com/books?search=" + title;
        try {
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

            GutendexResponse response = objectMapper.readValue(jsonResponse, GutendexResponse.class);

            return response.getResults();
        } catch (Exception e) {
            System.out.println("Erro ao realizar a consulta ou processar a resposta: " + e.getMessage());
            return null;
        }
    }

    public void storeBook(GutendexResponse.Book bookResponse) {
        Livro livro = new Livro();
        livro.setTitle(bookResponse.getTitle());

        if (bookResponse.getAuthors() != null && !bookResponse.getAuthors().isEmpty()) {
            List<Autor> autores = new ArrayList<>();
            for (GutendexResponse.Author author : bookResponse.getAuthors()) {
                Integer birthYear = author.getBirth_year();
                Integer deathYear = author.getDeath_year();

                Autor novoAutor = new Autor(author.getName(), birthYear, deathYear, livro);
                autores.add(novoAutor);
            }

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

        if (bookResponse.getDownloadCount() != null) {
            livro.setDownloadCount(bookResponse.getDownloadCount());
        } else {
            livro.setDownloadCount(0);
        }

        repositorioLivros.save(livro);
        System.out.println("Livro salvo: " + livro.getTitle());
    }
}