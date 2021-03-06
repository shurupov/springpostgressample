package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Evgeny Shurupov
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
