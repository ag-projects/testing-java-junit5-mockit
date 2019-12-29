package com.agharibi.petclinic.controllers;

import com.agharibi.petclinic.ControllerTests;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


class IndexControllerTest implements ControllerTests {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test proper view name is returned from index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong view is returned");
        assertEquals("index", controller.index(), () -> "Wrong view is returned using lambda functionality");

        // assertJ assertion
        assertThat(controller.index()).isEqualTo("index");
    }

    @DisplayName("Test exception")
    @Test
    void erroneousHandler() {
        assertThrows(ValueNoFoundException.class, () -> {
            controller.erroneousHandler();
        });
    }

    @Test
    void testTimeouts() {
        assertTimeout(Duration.ofMillis(1000), () -> {
                Thread.sleep(100);
                System.out.println("The second statement in test method");
            });
    }

    @Disabled
    @Test
    void testTimeoutPrempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("The second statement in test preemptive method");
        });
    }

    @Test
    void testAssumptionsTrue() {
        assumeTrue("STAGE".equalsIgnoreCase(System.getenv("STAGE_ENV")));
    }

    @Test
    void testEnvAssumptionTrue() {
        assumeTrue("DEV".equalsIgnoreCase("DEV"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testOnMacOS() {

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testOnWindowsOS() {

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testOnJava8() {

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testOnJava11() {

    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "Armen")
    @Test
    void testIfUserIsArmen() {

    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "Fred")
    @Test
    void testIfUserIsFred() {

    }
}
