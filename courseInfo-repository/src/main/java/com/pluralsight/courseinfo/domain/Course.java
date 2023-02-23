package com.pluralsight.courseinfo.domain;

public record Course(String id, String name, Long length, String url) {

    public Course {
        filled(id);
        filled(name);
        filled(url);
    }

    public static void filled(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("No value Present!!!");
        }
    }

}
