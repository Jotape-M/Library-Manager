package com.joaopedro.librarymanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nome;

    @Column(nullable = false, length = 50)
    private String endereco;

    @Column(nullable = false, length = 40)
    private String cidade;

    @Column(nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Aluguel> aluguelList;
}
