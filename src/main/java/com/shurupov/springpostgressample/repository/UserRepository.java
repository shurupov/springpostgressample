package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstOrLastOrDepartment_Id(String first, String last, Long departmentId);

}
