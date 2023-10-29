package com.web2.RoundRobin.controller;

import com.web2.RoundRobin.model.DTO.MatchUpdateDTO;
import com.web2.RoundRobin.service.MatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PutMapping("/match")
    public void updateMatchResult(@RequestBody MatchUpdateDTO matchUpdateDTO) {
        matchService.updateResult(matchUpdateDTO);
    }

}
