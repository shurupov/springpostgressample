package com.shurupov.springpostgressample.domain.department;

public class DepartmentDTO {

    private String id;

    private String name;

    private String description;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Department department) {
        id = String.valueOf(department.getId());
        name = department.getName();
        description = department.getDescription();
    }

    public DepartmentDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
