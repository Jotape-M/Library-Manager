package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.builder.EditoraDTOBuilder;
import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.mapper.EditoraMapper;
import com.joaopedro.librarymanager.model.Editora;
import com.joaopedro.librarymanager.repository.EditoraRepository;
import com.joaopedro.librarymanager.repository.LivroRepository;
import com.joaopedro.librarymanager.service.impl.EditoraServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditoraServiceTest {

    @Spy
    private EditoraMapper editoraMapper = Mappers.getMapper(EditoraMapper.class);

    @Mock
    private EditoraRepository editoraRepository;

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private EditoraServiceImpl editoraService;

    private EditoraDTOBuilder editoraDTOBuilder;

    @BeforeEach
    void setUp() {
        editoraDTOBuilder = EditoraDTOBuilder.builder().build();
    }

    @Test
    void whenNewEditoraIsInformedThenItShouldBeCreated() {
        //given
        EditoraDTO expectedEditoraToCreateDTO = editoraDTOBuilder.buildEditoraDTO();
        Editora expectedCreatedEditora = editoraMapper.toModel(expectedEditoraToCreateDTO);

        //when
        when(editoraRepository.save(expectedCreatedEditora)).thenReturn(expectedCreatedEditora);

        EditoraDTO createdEditoraDTO = editoraService.create(expectedEditoraToCreateDTO);

        //then
        assertThat(createdEditoraDTO, is(equalTo(expectedEditoraToCreateDTO)));
    }

    @Test
    void whenListPaginatedEditorasIsCalledThenItShouldBeReturned() {
        //given
        EditoraDTO expectedFoundEditoraDTO = editoraDTOBuilder.buildEditoraDTO();
        Editora expectedFoundEditora = editoraMapper.toModel(expectedFoundEditoraDTO);

        Pageable pageable = PageRequest.of(0, 5);

        //when
        when(editoraRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(expectedFoundEditora)));

        Page<EditoraDTO> foundEditorasDTO = editoraService.findAll(pageable);

        //then
        assertThat(foundEditorasDTO.getContent().size(), is(1));
        assertThat(foundEditorasDTO.getContent().get(0), is(equalTo(expectedFoundEditoraDTO)));
    }

    @Test
    void whenListPaginatedEditorasIsCalledThenAnEmptyListShouldBeReturned() {
        //given
        Pageable pageable = PageRequest.of(0, 5);

        //when
        when(editoraRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.EMPTY_LIST));

        Page<EditoraDTO> foundAuthorsDTO = editoraService.findAll(pageable);

        //then
        assertThat(foundAuthorsDTO.getContent().size(), is(0));
    }

    @Test
    void whenListEditorasIsCalledThenItShouldBeReturned() {
        //given
        EditoraDTO expectedFoundEditoraDTO = editoraDTOBuilder.buildEditoraDTO();
        Editora expectedFoundEditora = editoraMapper.toModel(expectedFoundEditoraDTO);

        //when
        when(editoraRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundEditora));

        List<EditoraDTO> foundEditorasDTO = editoraService.findAll();

        //then
        assertThat(foundEditorasDTO.size(), is(1));
        assertThat(foundEditorasDTO.get(0), is(equalTo(expectedFoundEditoraDTO)));
    }

    @Test
    void whenListEditorasIsCalledThenAnEmptyListShouldBeReturned() {
        //when
        when(editoraRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<EditoraDTO> foundAuthorsDTO = editoraService.findAll();

        //then
        assertThat(foundAuthorsDTO.size(), is(0));
    }

    @Test
    void whenEditoraIsInformedThenItShouldBeUpdated() {
        //given
        EditoraDTO expectedEditoraToUpdateDTO = editoraDTOBuilder.buildEditoraDTO();
        Editora expectedUpdatedEditora = editoraMapper.toModel(expectedEditoraToUpdateDTO);

        //when
        when(editoraRepository.save(expectedUpdatedEditora)).thenReturn(expectedUpdatedEditora);

        EditoraDTO updatedEditoraDTO = editoraService.create(expectedEditoraToUpdateDTO);

        //then
        assertThat(updatedEditoraDTO, is(equalTo(expectedEditoraToUpdateDTO)));
    }
}
