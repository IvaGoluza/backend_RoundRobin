package com.web2.RoundRobin.repository;

import com.web2.RoundRobin.model.Tournament;
import com.web2.RoundRobin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Tournament getTournamentByTourNameAndUser(String tourName, User user);
}
