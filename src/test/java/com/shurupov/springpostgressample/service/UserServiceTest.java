package com.shurupov.springpostgressample.service;

import com.shurupov.springpostgressample.dto.UserDTO;
import com.shurupov.springpostgressample.entity.Department;
import com.shurupov.springpostgressample.entity.User;
import com.shurupov.springpostgressample.repository.DepartmentRepository;
import com.shurupov.springpostgressample.repository.UserRepository;
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
public class UserServiceTest {

    private UserService userService;
    private DepartmentService departmentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() {

        departmentService = new DepartmentService(departmentRepository);
        userService = new UserService(userRepository, departmentService);

        Department department1 = departmentRepository.save(new Department("Developing", "Developing department"));
        Department department2 = departmentRepository.save(new Department("QA", "QA department"));

        userRepository.save(new User(department1, "John", "Johnson"));
        userRepository.save(new User(department1, "Tom", "Smith"));
        userRepository.save(new User(department2, "Evgeny", "Shurupov"));
    }

    @Test
    public void testSearch() {
        List<UserDTO> users = userService.findUsers("Evgeny", null, null);
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getFirst(), "Evgeny");

        users = userService.findUsers("o", null, null);
        assertEquals(users.size(), 2);

        users = userService.findUsers(null, "s", null);
        assertEquals(users.size(), 3);

        users = userService.findUsers(null, null, null);
        assertEquals(users.size(), 3);
    }

}
