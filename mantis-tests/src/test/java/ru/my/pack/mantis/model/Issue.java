package ru.my.pack.mantis.model;

public class Issue {
    private int id;
    private String suumary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return suumary;
    }

    public Issue withSummary(String suumary) {
        this.suumary = suumary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
