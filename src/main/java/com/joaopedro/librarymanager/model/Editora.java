package com.joaopedro.librarymanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "editora")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nome;

    @Column(nullable = false, length = 40)
    private String cidade;

    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private List<Livro> livros;
}
