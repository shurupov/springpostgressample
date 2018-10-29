package com.shurupov.springpostgressample.dto;

import com.shurupov.springpostgressample.entity.Department;

public class DepartmentDTO {

    private Long id;

    private String name;

    private String description;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Department department) {
        id = department.getId();
        name = department.getName();
        description = department.getDescription();
    }

    public DepartmentDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentDTO department = (DepartmentDTO) o;

        if (id != null ? !id.equals(department.id) : department.id != null) return false;
        if (name != null ? !name.equals(department.name) : department.name != null) return false;
        return description != null ? description.equals(department.description) : department.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
