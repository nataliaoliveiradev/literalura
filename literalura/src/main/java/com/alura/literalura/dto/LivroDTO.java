package com.alura.literalura.dto;

import com.alura.literalura.titulo.Livro;
import com.alura.literalura.titulo.Autor;

import java.util.List;
import java.util.stream.Collectors;

public class LivroDTO {
    private Long id;
    private String title;
    private String language;
    private String downloadLink;
    private Integer downloadCount;
    private List<String> authors;

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.title = livro.getTitle();
        this.language = livro.getLanguage();
        this.downloadLink = livro.getDownloadLink();
        this.downloadCount = livro.getDownloadCount();
        this.authors = livro.getAuthors().stream()
                .map(Autor::getName)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "LivroDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", downloadLink='" + downloadLink + '\'' +
                ", downloadCount=" + downloadCount +
                ", authors=" + authors +
                '}';
    }
}
