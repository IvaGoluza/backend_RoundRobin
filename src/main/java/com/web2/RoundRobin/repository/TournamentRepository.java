package com.web2.RoundRobin.repository;

import com.web2.RoundRobin.model.DTO.TournamentResponseDTO;
import com.web2.RoundRobin.model.Tournament;
import com.web2.RoundRobin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Tournament getTournamentById(Long id);
    List<Tournament> getAllByUserId(Long id);

    Optional<Tournament> findByUUID(String uuid);
}
