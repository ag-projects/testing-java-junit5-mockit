package com.agharibi.petclinic.controllers;

import com.agharibi.petclinic.fauxspring.Model;
import com.agharibi.petclinic.fauxspring.ModelMap;
import com.agharibi.petclinic.fauxspring.ModelMapImpl;
import com.agharibi.petclinic.model.Speciality;
import com.agharibi.petclinic.model.Vet;
import com.agharibi.petclinic.services.SpecialtyService;
import com.agharibi.petclinic.services.VetService;
import com.agharibi.petclinic.services.map.SpecialityMapService;
import com.agharibi.petclinic.services.map.VetMapService;
import com.agharibi.petclinic.services.springdatajpa.SpecialitySDJpaService;
import com.agharibi.petclinic.services.springdatajpa.VetSDJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest {

    private VetService service;
    private SpecialtyService specialtyService;
    private VetController controller;

    @BeforeEach
    void setUp() {

        specialtyService = new SpecialityMapService();
        service = new VetMapService(specialtyService);
        controller = new VetController(service);

        Vet charlie = new Vet(1L, "Charlie", "Thompson", null);
        Vet alexis = new Vet(2L, "Alexis", "Ta", null);

        service.save(charlie);
        service.save(alexis);
    }

    @Disabled
    @Test
    void listVets() {
        ModelMapImpl model = new ModelMapImpl();
        String view = controller.listVets(model);

        assertThat("vets/index").isEqualTo(view);

        Set modelAttributes = (Set) (model).getMap().get("vets");
        assertThat(modelAttributes).isEqualTo(2);
    }
}
