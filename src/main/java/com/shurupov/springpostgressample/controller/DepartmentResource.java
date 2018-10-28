package com.shurupov.springpostgressample.controller;

import com.shurupov.springpostgressample.domain.department.DepartmentDTO;
import com.shurupov.springpostgressample.domain.department.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {

    private static final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findDepartments() {
        log.debug("REST request to list Departments");
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {
        log.debug("REST request to get Department with id {}", id);
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> post(@RequestBody DepartmentDTO departmentDTO) {
        log.debug("REST request to add Department: {}", departmentDTO);

        DepartmentDTO department = departmentService.create(departmentDTO);
        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .buildAndExpand(department.getId())
                .toUri();

        return ResponseEntity.created(uri).body(department);

    }

    @PutMapping("/{id}")
    ResponseEntity<DepartmentDTO> put(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        log.debug("REST request to update Department with id {}: {}", id, departmentDTO);
        DepartmentDTO department = departmentService.edit(id, departmentDTO);
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(department);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("REST request to remove Department with id {}", id);
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
