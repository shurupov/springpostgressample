package com.shurupov.springpostgressample.domain.user;

import com.shurupov.springpostgressample.domain.department.Department;

import javax.persistence.*;

@Entity
@Table(name = "sb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    private String first;

    private String last;

    public User() {
    }

    public User(Department department, String first, String last) {
        this.department = department;
        this.first = first;
        this.last = last;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
