package com.shurupov.springpostgressample.service;

import com.shurupov.springpostgressample.dto.DepartmentDTO;
import com.shurupov.springpostgressample.entity.Department;
import com.shurupov.springpostgressample.repository.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Shurupov
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentServiceTest {

    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() {
        departmentService = new DepartmentService(departmentRepository);
        departmentRepository.save(new Department(2L, "Developing", "Developing department"));
    }

    @Test
    public void testFind() {
        List<DepartmentDTO> departments = departmentService.findAll();
        assertEquals(departments.size(), 1);

        DepartmentDTO department = departmentService.findDTOById(2L);
        assertEquals(department.getName(), "Developing");
    }

}
