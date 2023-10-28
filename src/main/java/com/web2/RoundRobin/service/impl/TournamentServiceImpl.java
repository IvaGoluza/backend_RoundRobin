package com.web2.RoundRobin.service.impl;

import com.web2.RoundRobin.model.*;
import com.web2.RoundRobin.model.DTO.TournamentDTO;
import com.web2.RoundRobin.model.DTO.TournamentResponseDTO;
import com.web2.RoundRobin.repository.*;
import com.web2.RoundRobin.service.TournamentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final UserRepository userRepository;

    private final ScoreSystemRepository scoreSystemRepository;

    private final TournamentRepository tournamentRepository;

    private final TeamRepository teamRepository;

    private final MatchRepository matchRepository;

    private final RoundRepository roundRepository;

    private final Map<Integer, int[][][]> roundRobin = new HashMap<>();


    @Autowired
    public TournamentServiceImpl(UserRepository userRepository, ScoreSystemRepository scoreSystemRepository, TournamentRepository tournamentRepository, TeamRepository teamRepository, MatchRepository matchRepository, RoundRepository roundRepository) {
        this.userRepository = userRepository;
        this.scoreSystemRepository = scoreSystemRepository;
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.roundRepository = roundRepository;
        int[][][] data4 = {
                {{1, 4}, {2, 3}},
                {{2, 4}, {3, 1}},
                {{3, 4}, {1, 2}}
        };
        this.roundRobin.put(4, data4);
        int[][][] data5 = {
                {{1, 1}, {2, 5}, {3, 4}},
                {{5, 1}, {4, 4}, {2, 3}},
                {{1, 4}, {5, 3}, {2, 2}},
                {{3, 1}, {4, 2}, {5, 5}},
                {{1, 2}, {3, 3}, {4, 5}}
        };
        this.roundRobin.put(5, data5);
        int[][][] data6 = {
                {{1, 6}, {2, 5}, {3, 4}},
                {{5, 1}, {6, 4}, {2, 3}},
                {{1, 4}, {5, 3}, {6, 2}},
                {{3, 1}, {4, 2}, {5, 6}},
                {{1, 2}, {3, 6}, {4, 5}}
        };
        this.roundRobin.put(6, data6);
        int[][][] data7 = {
                {{1, 1}, {2, 7}, {3, 6}, {4, 5}},
                {{7, 1}, {6, 6}, {2, 5}, {3, 4}},
                {{1, 6}, {7, 5}, {4, 4}, {2, 3}},
                {{5, 1}, {6, 4}, {7, 3}, {2, 2}},
                {{1, 4}, {5, 3}, {6, 2}, {7, 7}},
                {{3, 1}, {4, 2}, {5, 5}, {6, 7}},
                {{1, 2}, {3, 3}, {4, 7}, {5, 6}}
        };
        this.roundRobin.put(7, data7);
        int[][][] data8 = {
                {{1, 8}, {2, 7}, {3, 6}, {4, 5}},
                {{7, 1}, {8, 6}, {2, 5}, {3, 4}},
                {{1, 6}, {7, 5}, {8, 4}, {2, 3}},
                {{5, 1}, {6, 4}, {7, 3}, {8, 2}},
                {{1, 4}, {5, 3}, {6, 2}, {7, 8}},
                {{3, 1}, {4, 2}, {5, 8}, {6, 7}},
                {{1, 2}, {3, 8}, {4, 7}, {5, 6}}
        };
        this.roundRobin.put(8, data8);
    }



    @Override
    public TournamentResponseDTO saveTournament(TournamentDTO tournamentDTO) {

        // save scores system
        String[] scores = tournamentDTO.getScoreSystem().split("/");
        int win = Integer.parseInt(scores[0]);
        int tie = Integer.parseInt(scores[1]);
        int loss = Integer.parseInt(scores[2]);
        ScoreSystem scoreSystem = new ScoreSystem();
        scoreSystem.setWin(win);
        scoreSystem.setTie(tie);
        scoreSystem.setLoss(loss);


        // save tournament
        Tournament tournament = new Tournament();
        User user = userRepository.findById(tournamentDTO.getUserId()).orElseThrow(() ->
                new EntityNotFoundException("User with id " + tournamentDTO.getUserId() + " does not exist.")
        );
        tournament.setUser(user);
        tournament.setTourName(tournamentDTO.getTourName());
        tournament.setScoreSystem(scoreSystem);
        scoreSystem.setTournament(tournament);
        scoreSystemRepository.save(scoreSystem);      //saving scores system
        tournament = tournamentRepository.save(tournament);        //saving tournament


        // save the teams
        String teams = tournamentDTO.getTeams();
        int key;
        List<String> teamsAr = new ArrayList<>();

        if(teams.contains(";")) {
            teamsAr.addAll(List.of(teams.split(";")));
        } else if (teams.contains("\n")) {
            teamsAr.addAll(List.of(teams.split("\n")));
        }
        key = teamsAr.size();
        List<Long> teamsIDs = new ArrayList<>();
        for (String name: teamsAr) {
            Team team = new Team();
            team.setTeamName(name);
            team.setTeamScore(0);
            team.setTourId(tournament.getId());
            team = teamRepository.save(team);
            teamsIDs.add(team.getId());
        }


        // save rounds and matches
        for (int[][] roundIdx : roundRobin.get(key)) {
            Round round = new Round();
            round.setTournament(tournament);
            round = roundRepository.save(round);        //saving round
            for (int[] matchIdx : roundIdx) {
                 Match match = new Match();
                 match.setRound(round);
                 match.setTeam1(teamsIDs.get(matchIdx[0] - 1));
                 match.setTeam2(teamsIDs.get(matchIdx[1] - 1));
                 matchRepository.save(match);           //saving match
            }

        }
        return null;
    }

    @Override
    public List<TournamentResponseDTO> getTournaments(Long userId) {
        return null;
    }
}
