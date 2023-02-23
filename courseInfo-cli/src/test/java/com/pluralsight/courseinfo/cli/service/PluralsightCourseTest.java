package com.pluralsight.courseinfo.cli.service;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            00:12:22.1234, 12
            02:52:24, 172
            00:00:06.565, 0
            """)
    void durationInMinutes(String inputDuration, long expected) {
        PluralsightCourse course = new PluralsightCourse("id", "Testing course", inputDuration, "https://urs.com", true);
        assertEquals(expected, course.durationInMinutes());
    }
}