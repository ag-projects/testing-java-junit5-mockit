package com.agharibi.petclinic.services.map;

import com.agharibi.petclinic.model.Owner;
import com.agharibi.petclinic.model.PetType;
import com.agharibi.petclinic.services.PetService;
import com.agharibi.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Map Service Test - ")
class OwnerMapServiceTest {

    PetTypeService petTypeService;
    PetService petService;
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero() {
        int ownersCount = ownerMapService.findAll().size();
        assertThat(ownersCount).isZero();
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType dog = new PetType(1L, "Dog");
            PetType cat = new PetType(2L, "Cat");
            petTypeService.save(dog);
            petTypeService.save(cat);
        }

        @Test
        void testPetCount() {
            int petsCount = petTypeService.findAll().size();
            assertThat(petsCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save Owners Test - ")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));
            }

            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Jackie", "Ta");
                Owner savedOwner = ownerMapService.save(owner);
                assertThat(savedOwner).isNotNull();
            }

            @DisplayName("Save Owners Tests - ")
            @Nested
            class FindOwnersTests {

                @DisplayName("Find Owner")
                @Test
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1L);
                    assertThat(foundOwner).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {
                    Owner foundOwner = ownerMapService.findById(2L);
                    assertThat(foundOwner).isNull();
                }
            }
        }
    }

    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero() {
        int ownersCount = ownerMapService.findAll().size();
        assertThat(ownersCount).isZero();
    }
}
