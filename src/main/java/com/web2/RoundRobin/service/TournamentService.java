package com.web2.RoundRobin.service;

import com.web2.RoundRobin.model.DTO.TournamentDTO;
import com.web2.RoundRobin.model.DTO.TournamentResponseDTO;

import java.util.List;

public interface TournamentService {

    TournamentResponseDTO saveTournament(TournamentDTO tournamentDTO);

    List<TournamentResponseDTO> getTournaments(Long userId);

    TournamentResponseDTO getTournament(Long tourId);

    TournamentResponseDTO getTournamentByLink(String uuid);
}
