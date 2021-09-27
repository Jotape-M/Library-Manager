package com.joaopedro.librarymanager.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private LocalDate lancamento;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "editora")
    private Editora editora;
}
