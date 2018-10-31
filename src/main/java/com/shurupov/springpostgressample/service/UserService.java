package com.shurupov.springpostgressample.service;

import com.shurupov.springpostgressample.entity.Department;
import com.shurupov.springpostgressample.entity.User;
import com.shurupov.springpostgressample.dto.UserDTO;
import com.shurupov.springpostgressample.repository.UserRepository;
import com.shurupov.springpostgressample.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service for managing user
 * @author Evgeny Shurupov
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final DepartmentService departmentService;

    @Autowired
    public UserService(UserRepository userRepository, DepartmentService departmentService) {
        this.userRepository = userRepository;
        this.departmentService = departmentService;
    }

    /**
     * Fuzzy search by first name, last name or department id.
     * @param first part of first name to search
     * @param last part of last name to search
     * @param departmentId department id
     * @return list of found UserDTO matched the search parameters
     * */
    @Transactional
    public List<UserDTO> findUsers(String first, String last, Long departmentId) {
        log.debug("Request to get list of Users by first '{}', last '{}', departmentId '{}'", first, last, departmentId);

        Department department;

        if (departmentId != null) {
            department = new Department();
            department.setId(departmentId);
        } else {
            department = null;
        }

        User userExample = new User(department, first, last);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<User> example = Example.of(userExample, matcher);

        List<User> found = userRepository.findAll(example);

        return Util.extractUserDTOListFromUserList(found);
    }

    @Transactional
    public UserDTO findById(Long id) {
        log.debug("Request to get User with id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User is not found"));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        log.debug("Request to add User: {}", userDTO);
        Department department = departmentService.findById(userDTO.getDepartmentId());
        return new UserDTO(userRepository.save(new User(department, userDTO.getFirst(), userDTO.getLast())));
    }

    @Transactional
    public UserDTO edit(Long id, UserDTO userDTO) {
        log.debug("Request to update User with id {}: {}", id, userDTO);
        User user = userRepository.findById(id).map(
                existing -> {
                    existing.setDepartment(departmentService.findById(userDTO.getDepartmentId()));
                    existing.setFirst(userDTO.getFirst());
                    existing.setLast(userDTO.getLast());
                    return userRepository.save(existing);
                }
        ).orElseThrow(() -> new EntityNotFoundException("User is not found"));
        return new UserDTO(user);
    }

    @Transactional
    public void delete(Long id) {
        log.debug("Request to remove User with id {}", id);
        userRepository.findById(id).map(
                existing -> {
                    userRepository.delete(existing);
                    return existing;
                }
        ).orElseThrow(() -> new EntityNotFoundException("User is not found"));
    }

}
