package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSave(){
        Department department = new Department(1L, "New department", "Description of new Department");
        department = departmentRepository.save(department);
        assertNotNull(department);
        assertEquals(department.getName(),"New department");
        assertEquals(department.getDescription(),"Description of new Department");
        assertNotNull(department.getId());

        Optional<Department> optionalDepartment = departmentRepository.findById(1L);
        assertTrue(optionalDepartment.isPresent());
        Department result = optionalDepartment.get();
        Long id = 1L;
        assertEquals(result.getId(),id);
    }

}
