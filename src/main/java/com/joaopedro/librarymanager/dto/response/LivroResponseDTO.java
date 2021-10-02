package com.joaopedro.librarymanager.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joaopedro.librarymanager.dto.EditoraDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 40)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 40)
    private String autor;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
    private LocalDate lancamento;

    @NotNull
    private Long quantidade;

    @NotNull
    private EditoraDTO editora;
}
