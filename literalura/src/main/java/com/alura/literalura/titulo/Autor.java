package com.alura.literalura.titulo;

import jakarta.persistence.*;

@Entity
public class Autor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name = "birth_year")
    private Integer birth_year; // Año de nacimiento


    @Column(name = "death_year")
    private Integer death_year; // Año de muerte


    // Relación ManyToOne con Book (un libro puede tener muchos autores)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id") // Clave foránea en la tabla Author
    private Livro livro;

    // Constructor sin argumentos
    public Autor() {

    }

    // Constructor con parámetros

    public Autor(String name, Integer birth_year, Integer death_year, Livro livro) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
        this.livro = livro;
    }


    // Getters y Setters


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

    public Livro getBook() {
        return livro;
    }

    public void setBook(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", birth_year=" + birth_year +
                ", name='" + name + '\'' +
                ", death_year=" + death_year +
                '}';
    }
}
