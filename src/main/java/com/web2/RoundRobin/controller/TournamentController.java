package com.web2.RoundRobin.controller;

import com.web2.RoundRobin.model.DTO.TournamentDTO;
import com.web2.RoundRobin.model.DTO.TournamentResponseDTO;
import com.web2.RoundRobin.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "https://frontend-roundrobin.vercel.app")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) { this.tournamentService = tournamentService; }

    @PostMapping("/tournaments")
    public ResponseEntity<TournamentResponseDTO> saveRequest(@RequestBody TournamentDTO tournamentDTO) {
        return ResponseEntity.ok(tournamentService.saveTournament(tournamentDTO));
    }
    @GetMapping("/tournaments/{userId}")
    public ResponseEntity<List<TournamentResponseDTO>> getTournaments(@PathVariable Long userId) {
        return ResponseEntity.ok(tournamentService.getTournaments(userId));
    }

    @GetMapping("/tournament/{tourId}")
    public ResponseEntity<TournamentResponseDTO> getTournament(@PathVariable Long tourId) {
        return ResponseEntity.ok(tournamentService.getTournament(tourId));
    }
    @GetMapping("/tournament-by-link/{uuid}")
    public ResponseEntity<TournamentResponseDTO> getTournament(@PathVariable String uuid) {
        return ResponseEntity.ok(tournamentService.getTournamentByLink(uuid));
    }

}
