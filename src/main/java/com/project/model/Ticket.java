package com.project.model;

public class Ticket {
    private String name;
    private String title;
    private String description;
    private String category;
    private String priority;
    private String status;
    private String username;
    private String solution;
    private String technician;
    private int id;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }
    
    public String getSolution() {return solution;}
    public void setSolution(String solution) {this.solution = solution;}
    
    public String getTechnician() {return technician;}
    public void setTechnician(String technician) {this.technician = technician;}
}