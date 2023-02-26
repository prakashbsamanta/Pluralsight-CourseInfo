package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {
    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    public static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);
    public static final String BASE_URI = "http://localhost:8022/";

    public static void main(String... args) {
        String databaseFileName = loadDatabaseFileName();
        LOG.info("Starting the Http Server with database {}", databaseFileName);
        CourseRepository courseRepository = CourseRepository.openCourseRepository(databaseFileName);
        ResourceConfig resourceConfig = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
    }

    private static String loadDatabaseFileName() {
        try (InputStream propertiesStream = CourseServer.class.getResourceAsStream("/server.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            return properties.getProperty("courseInfo.database");
        } catch (IOException e) {
            throw new IllegalStateException("Could not load database file");
        }
    }
}
