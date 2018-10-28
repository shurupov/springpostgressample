package com.shurupov.springpostgressample.util;

import com.shurupov.springpostgressample.domain.department.Department;
import com.shurupov.springpostgressample.domain.department.DepartmentDTO;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<DepartmentDTO> extractDepartmentDTOListFromDepartmentList(List<Department> departments) {
        List<DepartmentDTO> departmentDTOs = new ArrayList<>(departments.size());

        for (Department department : departments) {
            departmentDTOs.add(new DepartmentDTO(department));
        }

        return departmentDTOs;

    }

}
