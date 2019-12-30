package com.agharibi.petclinic.services.springdatajpa;

import com.agharibi.petclinic.model.Speciality;
import com.agharibi.petclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    private SpecialtyRepository repository;

    @InjectMocks  // injects dependent objects -> repository
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(repository, atLeastOnce()).delete(any(Speciality.class));
    }

    @Test
    void testDeleteByObjectBDD() {
        // given
        Speciality speciality = new Speciality();
        // when
        service.delete(speciality);
        // then
        then(repository).should().delete(any(Speciality.class));
    }

    @Test
    void testFindById() {
        Speciality speciality = new Speciality();
        when(repository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality specialityFound = service.findById(1L);
        assertThat(specialityFound).isNotNull();
        verify(repository, times(1)).findById(1L);
        verify(repository).findById(anyLong());
    }

    @Test
    void findByIdBddTest() {
        // given
        Speciality speciality = new Speciality();
        given(repository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality found = service.findById(1L);
        assertThat(found).isNotNull();

        // then
        then(repository).should(times(1)).findById(anyLong());
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdBDDTest() {
        //given none
        //when
        service.deleteById(anyLong());
        //then
        then(repository).should(times(1)).deleteById(anyLong());
    }

    @Test
    void deleteByAtLeastOnce() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(repository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByAtLeastOne() {

        //given
        //when
        service.deleteById(anyLong());
        service.deleteById(anyLong());
        //then
        then(repository).should(atLeastOnce()).deleteById(anyLong());
    }

    @Test
    void deleteByAtMostOnce() {
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        verify(repository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(2L);

        verify(repository, atLeastOnce()).deleteById(1L);
        verify(repository, never()).deleteById(8L);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("Error Occured")).when(repository).delete(any());
        assertThrows(RuntimeException.class, () -> repository.delete(new Speciality()));
        verify(repository).delete(any());

    }

    @Test
    void testFindByIdThrows() {
        given(repository.findById(anyLong())).willThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> service.findById(anyLong()));
        then(repository).should().findById(anyLong());
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("Error")).given(repository).delete(any());
        assertThrows(RuntimeException.class, () -> service.delete(any()));
        then(repository).should().delete(any());
    }

    @Test
    void testSaveLambda() {
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);
        // given
        given(repository.save(argThat(arg -> arg.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);
        // when
        Speciality returnedSpeciality = service.save(speciality);
        // then
        assertThat(returnedSpeciality.getId()).isEqualTo(1L);
    }
}
