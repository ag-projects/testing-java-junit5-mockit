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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void testFindById() {
        Speciality speciality = new Speciality();
        when(repository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality specialityFound = service.findById(1L);
        assertThat(specialityFound).isNotNull();
        verify(repository, times(1)).findById(1L);
        verify(repository).findById(anyLong());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteByAtLeastOnce() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(repository, atLeastOnce()).deleteById(1L);
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
}
