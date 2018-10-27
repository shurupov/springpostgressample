package com.shurupov.springpostgressample.domain.user;

import javax.persistence.*;

@Entity
@Table(name = "sb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first;

    private String last;

    public User() {
    }

    public User(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
