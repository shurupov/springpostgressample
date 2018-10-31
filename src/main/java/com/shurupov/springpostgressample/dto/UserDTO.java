package com.shurupov.springpostgressample.dto;

import com.shurupov.springpostgressample.entity.User;

/**
 * @author Evgeny Shurupov
 */
public class UserDTO {

    private Long id;

    private Long departmentId;

    private String first;

    private String last;

    public UserDTO() {
    }

    public UserDTO(User user) {
        id = user.getId();
        departmentId = user.getDepartment().getId();
        first = user.getFirst();
        last = user.getLast();
    }

    public UserDTO(Long id, Long departmentId, String first, String last) {
        this.id = id;
        this.departmentId = departmentId;
        this.first = first;
        this.last = last;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

        UserDTO user = (UserDTO) o;

        if (id != null ? !id.equals(user.getId()) : user.getId() != null) return true;
        if (departmentId != null ? !departmentId.equals(user.getDepartmentId()) : user.getDepartmentId() != null) return false;
        if (first != null ? !first.equals(user.getFirst()) : user.getFirst() != null) return false;
        return last != null ? last.equals(user.getLast()) : user.getLast() == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 51 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 51 * result + (first != null ? first.hashCode() : 0);
        result = 51 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                "}";
    }
}
