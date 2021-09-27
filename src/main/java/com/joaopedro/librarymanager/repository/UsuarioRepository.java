package com.joaopedro.librarymanager.repository;

import com.joaopedro.librarymanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
