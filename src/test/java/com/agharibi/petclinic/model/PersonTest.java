package com.agharibi.petclinic.model;

import com.agharibi.petclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

    @Test
    void groupAssertions() {
        // given
        Person person = new Person(1L, "James", "Stevens");

        // then
        assertAll("Testing person properties",
            () -> assertEquals( "James", person.getFirstName()),
            () -> assertEquals( "Stevens", person.getLastName()));
    }

    @Test
    void groupAssertionsMessages() {
        Person person = new Person(2L, "Armen", "Gharibi");

        assertAll("Group assertions with messages",
            () -> assertTrue(2L == person.getId(), "Person Id failed"),
            () -> assertEquals("Armen", person.getFirstName(), "First name test failed"),
            () -> assertEquals("Gharibi", person.getLastName(), "Last name test failed"));
    }

}
