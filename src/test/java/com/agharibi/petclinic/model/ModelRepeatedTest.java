package com.agharibi.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface ModelRepeatedTest {

    @BeforeEach
    default void beforeEachConsoleOutput(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.err.println("Running test - "
            + testInfo.getDisplayName() + " | " + repetitionInfo.getCurrentRepetition());
    }
}
