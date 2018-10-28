package com.shurupov.springpostgressample.domain.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department create(String name, String description) {
        return departmentRepository.save(new Department(name, description));
    }

    public Department edit(Long id, Department d) {
        return departmentRepository.findById(id).map(
            existing -> {
                existing.setName(d.getName());
                existing.setDescription(d.getDescription());
                return departmentRepository.save(existing);
            }
        ).orElseThrow(() -> new EntityNotFoundException("Department is not found"));
    }

    public void delete(Long id) {
        departmentRepository.findById(id).map(
            existing -> {
                departmentRepository.delete(existing);
                return existing;
            }
        ).orElseThrow(() -> new EntityNotFoundException("Department is not found"));
    }
}
