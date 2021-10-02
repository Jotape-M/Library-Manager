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
    @Size(min = 3, max = 40)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 50)
    private String endereco;

    @NotBlank
    @Size(min = 3, max = 40)
    private String cidade;

    @NotBlank
    @Email
    @Size(min = 3, max = 50)
    private String email;
}
