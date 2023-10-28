package com.web2.RoundRobin.repository;

import com.web2.RoundRobin.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team getTeamByTeamNameAndTourId(String teamName, Long tourId);
}
