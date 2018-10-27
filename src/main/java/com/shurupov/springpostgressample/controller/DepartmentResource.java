package com.shurupov.springpostgressample.controller;

import com.shurupov.springpostgressample.domain.department.Department;
import com.shurupov.springpostgressample.domain.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> findDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return departmentService.findById(id).map(ResponseEntity::ok).orElseThrow(() -> new EntityNotFoundException("Department is not found"));
    }

    @PostMapping
    public ResponseEntity<Department> post(@RequestBody Department d) {

        Department department = departmentService.create(d.getName(), d.getDescription());

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(department.getId()).toUri();

        return ResponseEntity.created(uri).body(department);

    }
}
