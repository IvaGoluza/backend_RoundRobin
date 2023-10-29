package com.web2.RoundRobin.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TournamentResponseDTO {

    private Long id;

    private String link;

    private String tourName;

    private UserResponseDTO user;

    private ScoreSystemDTO scoreSystem;

    private List<RoundDTO> rounds;

}
