package com.isa.wildcards.repository;

import com.isa.wildcards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String userName);

    User findByUsername(String username);
}
