package com.web2.RoundRobin.service.impl;

import com.web2.RoundRobin.model.DTO.MatchUpdateDTO;
import com.web2.RoundRobin.model.Match;
import com.web2.RoundRobin.model.ScoreSystem;
import com.web2.RoundRobin.model.Team;
import com.web2.RoundRobin.repository.MatchRepository;
import com.web2.RoundRobin.repository.TeamRepository;
import com.web2.RoundRobin.repository.TournamentRepository;
import com.web2.RoundRobin.service.MatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    private final TeamRepository teamRepository;

    private final TournamentRepository tournamentRepository;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository, TournamentRepository tournamentRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public void updateResult(MatchUpdateDTO matchUpdateDTO) {
        Match match = matchRepository.findById(matchUpdateDTO.getMatchId()).orElseThrow(
                () -> new EntityNotFoundException("Match with id " + matchUpdateDTO.getMatchId() + " does not exist."));
        match.setResult(matchUpdateDTO.getScore());
        int team1score = Integer.parseInt(matchUpdateDTO.getScore().split(":")[0]);
        int team2score = Integer.parseInt(matchUpdateDTO.getScore().split(":")[1]);
        ScoreSystem scoreSystem = tournamentRepository.getTournamentById(matchUpdateDTO.getTourId()).getScoreSystem();
        Team team1 = match.getTeam1();
        Team team2 = match.getTeam2();
        if (team1score > team2score) {
            team1.setTeamScore(team1.getTeamScore() + scoreSystem.getWin());
            team2.setTeamScore(team2.getTeamScore() + scoreSystem.getLoss());
        } else if (team1score < team2score) {
            team1.setTeamScore(team1.getTeamScore() + scoreSystem.getLoss());
            team2.setTeamScore(team2.getTeamScore() + scoreSystem.getWin());
        } else {
            team1.setTeamScore(team1.getTeamScore() + scoreSystem.getTie());
            team2.setTeamScore(team2.getTeamScore() + scoreSystem.getTie());
        }
        teamRepository.save(team1);
        teamRepository.save(team2);
        matchRepository.save(match);
    }
}
