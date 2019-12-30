package com.agharibi.petclinic.services.springdatajpa;

import com.agharibi.petclinic.model.Visit;
import com.agharibi.petclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(repository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();
        verify(repository).findAll();
        assertThat(foundVisits).isNotNull();
        assertThat(foundVisits).hasSize(1);
    }

    @Test
    void findAllBDD() {
        // given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(repository.findAll()).willReturn(visits);

        // when
        Set<Visit> foundVisits = service.findAll();

        // then
        then(repository).should().findAll();
        assertThat(foundVisits).hasSize(1);
    }

    @Test
    void findById() {
        Visit visit = new Visit();
        when(repository.findById(1L)).thenReturn(Optional.of(visit));
        Visit found = service.findById(1L);
        assertThat(found).isNotNull();
    }

    @Test
    void findByIdBDD() {
        // given
        Visit visit = new Visit();
        given(repository.findById(anyLong())).willReturn(Optional.of(visit));
        // when
        Visit found = service.findById(anyLong());
        // then
        then(repository).should().findById(anyLong());
        assertThat(found).isNotNull();
    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(repository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(new Visit());
        verify(repository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    void saveBDD() {
        //given
        Visit visit = new Visit();
        given(repository.save(any(Visit.class))).willReturn(visit);
        //when
        Visit savedVisit = service.save(visit);
        //then
        then(repository).should().save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(repository).delete(any(Visit.class));
    }

    @Test
    void deleteBDD() {
        //given
        Visit visit = new Visit();
        //when
        service.delete(visit);
        //then
        then(repository).should().delete(visit);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteByIdDBB() {
        //given
        //when
        service.deleteById(anyLong());
        //then
        then(repository).should(atLeastOnce()).deleteById(anyLong());
    }
}
