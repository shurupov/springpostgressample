package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
