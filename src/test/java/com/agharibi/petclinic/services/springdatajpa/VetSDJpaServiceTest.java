package com.agharibi.petclinic.services.springdatajpa;

import com.agharibi.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock  // repository interface
    VetRepository vetRepository;

    @InjectMocks  // serviceImpl
    VetSDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(vetRepository, atLeastOnce()).deleteById(1L);
    }
}
