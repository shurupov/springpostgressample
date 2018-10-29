package com.shurupov.springpostgressample.entity;

import javax.persistence.*;

@Entity
@Table(name = "sb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.getId()) : user.getId() != null) return true;
        if (department != null ? !department.equals(user.getDepartment()) : user.getDepartment() != null) return false;
        if (first != null ? !first.equals(user.getFirst()) : user.getFirst() != null) return false;
        return last != null ? last.equals(user.getLast()) : user.getLast() == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 51 * result + (department != null ? department.hashCode() : 0);
        result = 51 * result + (first != null ? first.hashCode() : 0);
        result = 51 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", department=" + department +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                "}";
    }
}
