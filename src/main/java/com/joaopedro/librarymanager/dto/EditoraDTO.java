package com.joaopedro.librarymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditoraDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cidade;

}
