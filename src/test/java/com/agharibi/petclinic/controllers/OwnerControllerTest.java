package com.agharibi.petclinic.controllers;

import com.agharibi.petclinic.fauxspring.BindingResult;
import com.agharibi.petclinic.model.Owner;
import com.agharibi.petclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    OwnerService service;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController controller;

    @Test
    void processCreationFormHasErrors() {
        //given
        Owner owner = new Owner(1L, "Jim", "Stevens");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName =  controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }

    @Test
    void processCreationFormHasNoErrors() {
        //given
        Owner owner = new Owner(5L, "Jim", "Stevens");
        given(bindingResult.hasErrors()).willReturn(false);
        given(service.save(any())).willReturn(owner);

        //when
        String viewName =  controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }
}
