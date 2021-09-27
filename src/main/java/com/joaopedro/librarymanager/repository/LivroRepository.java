package com.joaopedro.librarymanager.repository;

import com.joaopedro.librarymanager.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
