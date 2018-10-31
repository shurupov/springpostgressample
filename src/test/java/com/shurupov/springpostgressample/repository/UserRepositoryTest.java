package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.Department;
import com.shurupov.springpostgressample.entity.User;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private Department department;

    @Before
    public void setUp() {
        userRepository.save(new User(null, "John", "Johnson"));
        userRepository.save(new User(null, "Tom", "Smith"));
        userRepository.save(new User(null, "Evgeny", "Shurupov"));
    }

    @Test
    public void saveUser() {
        User user = userRepository.save(new User(null, "Jack", "Black"));
        assertNotNull(user.getId());
        Optional<User> optionalUser = userRepository.findById(2L);
        assertTrue(optionalUser.isPresent());
        Long id = 2L;
        assertEquals(optionalUser.get().getId(), id);
    }

}
