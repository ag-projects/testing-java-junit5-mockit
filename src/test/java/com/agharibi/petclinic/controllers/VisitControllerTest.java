package com.agharibi.petclinic.controllers;

import com.agharibi.petclinic.model.Pet;
import com.agharibi.petclinic.model.Visit;
import com.agharibi.petclinic.services.PetService;
import com.agharibi.petclinic.services.VisitService;
import com.agharibi.petclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Spy
    PetMapService petService;

//    @Mock
//    PetService petService;

    @InjectMocks
    VisitController controller;

    @Test
    void loadPetWithVisit() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        petService.save(pet);
        given(petService.findById(anyLong())).willCallRealMethod();
//        given(petService.findById(anyLong())).willReturn(pet);

        //when
        Visit visit = controller.loadPetWithVisit(1L, model);

        //then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(1L);

        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void loadPetWithVisitWithStubbing() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        petService.save(pet);
        given(petService.findById(anyLong())).willReturn(pet);

        //when
        Visit visit = controller.loadPetWithVisit(1L, model);

        //then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(1L);

        verify(petService, times(1)).findById(anyLong());
    }
}
