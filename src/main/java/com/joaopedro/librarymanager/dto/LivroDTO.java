package com.joaopedro.librarymanager.dto;

import com.joaopedro.librarymanager.model.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String autor;

    @NotBlank
    private LocalDate lancamento;

    @NotBlank
    private int quantidade;

    @NotBlank
    private EditoraDTO editoraDTO;
}
