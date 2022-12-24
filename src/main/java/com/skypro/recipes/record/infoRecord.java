package com.skypro.recipes.record;

import java.time.LocalDate;

public class infoRecord {
    private final String name;
    private final String projectTitle;
    private final LocalDate projectDate;
    private final String description;

    public infoRecord(String name, String projectTitle, LocalDate projectDate, String description) {
        this.name = name;
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public LocalDate getProjectDate() {
        return projectDate;
    }

    public String getDescription() {
        return description;
    }
}
