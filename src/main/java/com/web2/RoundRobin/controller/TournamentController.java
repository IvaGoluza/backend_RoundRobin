package com.web2.RoundRobin.controller;

import com.web2.RoundRobin.model.DTO.TournamentResponseDTO;
import com.web2.RoundRobin.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) { this.tournamentService = tournamentService; }

    @GetMapping("/tournaments/{userId}")
    public ResponseEntity<List<TournamentResponseDTO>> getAdverts(@PathVariable Long userId) {
        return ResponseEntity.ok(tournamentService.getTournaments(userId));
    }

}
