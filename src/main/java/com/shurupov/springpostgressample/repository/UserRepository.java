package com.shurupov.springpostgressample.repository;

import com.shurupov.springpostgressample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Evgeny Shurupov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
