package com.agharibi.petclinic.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTest implements ModelRepeatedTest{


    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("repeated functional test")
    void repeatedTest() {
        // TODO - impl
    }

    @RepeatedTest(value = 5)
    void repeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + " : " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} | {totalRepetitions}")
    @DisplayName("assignment repeated test case  - ")
    void assignmentRepeated() {
        // TODO
    }
}
