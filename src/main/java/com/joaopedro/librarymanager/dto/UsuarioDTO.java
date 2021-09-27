package com.joaopedro.librarymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotBlank
    private String cidade;

    @NotBlank
    @Email
    private String email;
}
