package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() {
        Department department = new Department("Test department", "Test department description");
        department.setId(1L);
        departmentRepository.save(department);
    }

    @Test
    public void testFindById(){
        Optional<Department> department = departmentRepository.findById(1L);
        assertTrue(department.isPresent());
        Department result = department.get();
        Long id = 1L;
        assertEquals(result.getId(),id);
    }

    @Test
    public void testSave(){
        Department department = new Department("New department", "Description of new Department");
        department = departmentRepository.save(department);
        assertNotNull(department);
        assertEquals(department.getName(),"New department");
        assertEquals(department.getDescription(),"Description of new Department");
        assertNotNull(department.getId());
    }

}
