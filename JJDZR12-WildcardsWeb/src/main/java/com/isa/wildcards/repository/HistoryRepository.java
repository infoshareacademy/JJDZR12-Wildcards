package com.isa.wildcards.repository;

import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByUser(User user);
}
