package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
