package com.pluralsight.courseinfo.cli.service;

import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void savePluralsightCourses() {
        CourseRepository repository = new inMemoryCourseRepository();
        CourseStorageService courseStorageService = new CourseStorageService(repository);
        PluralsightCourse psc1 = new PluralsightCourse(
                "12",
                "pluralSightCourse1",
                "01:10:42.73",
                "/ps1.com",
                true
        );
        courseStorageService.savePluralsightCourses(List.of(psc1));
        Course expected = new Course("12", "pluralSightCourse1", 70L, "https://app.pluralsight.com/ps1.com", Optional.empty());
        assertEquals(List.of(expected), repository.getAllCourses());
    }

    static class inMemoryCourseRepository implements CourseRepository {

        private final List<Course> courses = new ArrayList<>();

        @Override
        public void saveCourse(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return courses;
        }

        @Override
        public void addNotes(String id, String notes) {
            throw new UnsupportedOperationException();
        }
    }

}