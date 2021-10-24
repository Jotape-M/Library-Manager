package com.joaopedro.librarymanager.repository;

import com.joaopedro.librarymanager.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

}
