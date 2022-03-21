package com.joaopedro.librarymanager.builder;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import lombok.Builder;

@Builder
public class EditoraDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String nome = "Panini";

    @Builder.Default
    private final String cidade = "São Paulo";

    public EditoraDTO buildEditoraDTO() {
        return new EditoraDTO(id, nome, cidade);
    }
}
