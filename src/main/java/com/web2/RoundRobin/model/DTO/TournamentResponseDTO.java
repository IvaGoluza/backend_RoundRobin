package com.web2.RoundRobin.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TournamentResponseDTO {

    private Long id;

    private String link;

    private String tournamentName;

    private UserResponseDTO user;

    private ScoreSystemDTO scoreSystem;





}
