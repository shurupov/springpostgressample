package com.shurupov.springpostgressample.service;

import com.shurupov.springpostgressample.entity.Department;
import com.shurupov.springpostgressample.entity.User;
import com.shurupov.springpostgressample.dto.UserDTO;
import com.shurupov.springpostgressample.repository.UserRepository;
import com.shurupov.springpostgressample.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    @Transactional
    public List<UserDTO> findUsers(String first, String last, Long departmentId) {
        log.debug("Request to get list if Users");
        List<User> users;
        if (first == null && last == null && departmentId == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByFirstOrLastOrDepartment_Id(first, last, departmentId);
        }
        return Util.extractUserDTOListFromUserList(users);
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
