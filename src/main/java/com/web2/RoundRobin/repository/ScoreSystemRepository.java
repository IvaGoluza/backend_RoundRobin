package com.web2.RoundRobin.repository;

import com.web2.RoundRobin.model.ScoreSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreSystemRepository extends JpaRepository<ScoreSystem, Long> {

}
