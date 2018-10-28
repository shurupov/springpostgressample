package com.shurupov.springpostgressample.domain.department;

import com.shurupov.springpostgressample.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> findAll() {
        log.debug("Request to get list if Departments");
        return Util.extractDepartmentDTOListFromDepartmentList(departmentRepository.findAll());
    }

    public DepartmentDTO findById(Long id) {
        log.debug("Request to get Department with id {}", id);
        Department department = departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Department is not found"));
        return new DepartmentDTO(department);
    }

    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        log.debug("Request to add Department: {}", departmentDTO);
        return new DepartmentDTO(departmentRepository.save(new Department(departmentDTO.getName(), departmentDTO.getDescription())));
    }

    public DepartmentDTO edit(Long id, DepartmentDTO departmentDTO) {
        log.debug("Request to update Department with id {}: {}", id, departmentDTO);
        Department department = departmentRepository.findById(id).map(
            existing -> {
                existing.setName(departmentDTO.getName());
                existing.setDescription(departmentDTO.getDescription());
                return departmentRepository.save(existing);
            }
        ).orElseThrow(() -> new EntityNotFoundException("Department is not found"));
        return new DepartmentDTO(department);
    }

    public void delete(Long id) {
        log.debug("Request to remove Department with id {}", id);
        departmentRepository.findById(id).map(
            existing -> {
                departmentRepository.delete(existing);
                return existing;
            }
        ).orElseThrow(() -> new EntityNotFoundException("Department is not found"));
    }
}
