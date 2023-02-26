package com.pluralsight.courseinfo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CourseTest {

    private Course course;

    @ParameterizedTest
    @CsvSource(textBlock = """
            '',name1,urljs.com
            id,'',anythinx.com
            id,name,''
            """)
    void attributeValidationTest(String id, String name, String url) {
        assertThrows(IllegalArgumentException.class, () -> new Course(id, name, 532L, url, Optional.empty()));

    }

    @Test
    void rejectBlankNotes() {
        assertThrows(IllegalArgumentException.class, () -> new Course("12312", "testName", 84L, "ghaskdjhf.com", Optional.of("")));
    }
}
