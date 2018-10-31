package com.shurupov.springpostgressample.util;

import com.shurupov.springpostgressample.entity.Department;
import com.shurupov.springpostgressample.dto.DepartmentDTO;
import com.shurupov.springpostgressample.entity.User;
import com.shurupov.springpostgressample.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class contains methods converting entities list to DTO list
 * @author Evgeny Shurupov
 */
public class Util {

    public static List<DepartmentDTO> extractDepartmentDTOListFromDepartmentList(List<Department> departments) {
        List<DepartmentDTO> departmentDTOs = new ArrayList<>(departments.size());

        for (Department department : departments) {
            departmentDTOs.add(new DepartmentDTO(department));
        }

        return departmentDTOs;
    }

    public static List<UserDTO> extractUserDTOListFromUserList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>(users.size());

        for (User user : users) {
            userDTOs.add(new UserDTO(user));
        }

        return userDTOs;
    }

}
