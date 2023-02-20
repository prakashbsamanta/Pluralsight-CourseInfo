package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRetriever {
    public static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("Course Retriever is Starting!!!");

        if (args.length == 0) {
            LOG.warn("Please Provide an author name as first argument");
            return;
        }

        try {
            retrieveCourses(args[0]);
        } catch (Exception e) {
            LOG.error("Unexpected exception: ", e);
        }
    }

    public static void retrieveCourses(String authorId) {
        LOG.info("Retrieving author courses: '{}'", authorId);

        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        String courseToStore = courseRetrievalService.getCourseFor(authorId);
        LOG.info("Retrieved the following courses: '{}'", courseToStore);
    }
}
