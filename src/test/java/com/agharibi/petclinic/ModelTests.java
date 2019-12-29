package com.agharibi.petclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("model")
public interface ModelTests {

    @BeforeEach
    default void beforeEachConsoleOutput(TestInfo testInfo) {
        System.err.println("Running test - " + testInfo.getDisplayName());
    }
}
