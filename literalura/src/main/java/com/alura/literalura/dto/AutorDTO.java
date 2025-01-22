package com.alura.literalura.dto;

import com.alura.literalura.titulo.Autor;

public class AutorDTO {
    private Long id;
    private String name;
    private Long bookId;
    private Integer birth_year;
    private Integer death_year;

    public AutorDTO(Autor autor) {
        this.id = autor.getId();
        this.name = autor.getName();
        this.bookId = autor.getBook() != null ? autor.getBook().getId() : null;
        this.birth_year = autor.getBirth_year();
        this.death_year = autor.getDeath_year();
    }

    public AutorDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    @Override
    public String toString() {
        return "AutorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}