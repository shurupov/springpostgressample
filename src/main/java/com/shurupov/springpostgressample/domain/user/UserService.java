package com.shurupov.springpostgressample.domain.user;

import com.shurupov.springpostgressample.domain.department.Department;
import com.shurupov.springpostgressample.domain.department.DepartmentDTO;
import com.shurupov.springpostgressample.domain.department.DepartmentRepository;
import com.shurupov.springpostgressample.domain.department.DepartmentService;
import com.shurupov.springpostgressample.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    public List<UserDTO> findAll() {
        log.debug("Request to get list if Users");
        return Util.extractUserDTOListFromUserList(userRepository.findAll());
    }

    public UserDTO findById(Long id) {
        log.debug("Request to get User with id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User is not found"));
        return new UserDTO(user);
    }

    public UserDTO create(UserDTO userDTO) {
        log.debug("Request to add User: {}", userDTO);
        Department department = departmentService.findById(userDTO.getDepartmentId());
        return new UserDTO(userRepository.save(new User(department, userDTO.getFirst(), userDTO.getLast())));
    }

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
