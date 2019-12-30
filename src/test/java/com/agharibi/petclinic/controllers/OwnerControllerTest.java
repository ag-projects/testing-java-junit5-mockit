package com.agharibi.petclinic.controllers;

import com.agharibi.petclinic.fauxspring.BindingResult;
import com.agharibi.petclinic.fauxspring.Model;
import com.agharibi.petclinic.model.Owner;
import com.agharibi.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
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

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(service.findAllByLastNameLike(stringArgumentCaptor.capture()))
            .willAnswer(invocationOnMock -> {
                List<Owner> owners = new ArrayList<>();
                String name = invocationOnMock.getArgument(0);
                if(name.equals("%Stevens%")) {
                    owners.add(new Owner(1L, "Jim", "Stevens"));
                    return owners;
                }
                else if(name.equals("%DontFindMe%")) {
                    return owners;
                }
                else if(name.equals("%FindMe%")) {
                    owners.add(new Owner(1L, "Jim", "Stevens"));
                    owners.add(new Owner(2L, "Nick", "Mullen"));
                    return owners;
                }
                throw new RuntimeException("Invalid argument");
            });
    }


    @Test
    void testProcessFindFormFound() {
        //given
        Owner owner = new Owner(1L, "Jim", "FindMe");
        //when
        String viewName = controller.processFindForm(owner, bindingResult, Mockito.mock(Model.class));
        //then
        assertThat("%" + owner.getLastName() + "%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
    }

    @Test
    void testProcessFindFormNotFound() {
        //given
        Owner owner = new Owner(1L, "Jim", "DontFindMe");
        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        //then
        assertThat("%" + owner.getLastName() + "%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }

    @Test
    void testProcessFindFormWildCardString() {
        //given
        Owner owner = new Owner(1L, "Jim", "Stevens");
        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        //then
        assertThat("%" + owner.getLastName() + "%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }

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
