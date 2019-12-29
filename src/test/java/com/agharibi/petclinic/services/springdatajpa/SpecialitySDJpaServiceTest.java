package com.agharibi.petclinic.services.springdatajpa;

import com.agharibi.petclinic.model.Speciality;
import com.agharibi.petclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    private SpecialtyRepository repository;

    @InjectMocks  // injects dependent objects -> repository
    SpecialitySDJpaService service;

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
