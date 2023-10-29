package com.web2.RoundRobin.model.DTO;

import com.web2.RoundRobin.model.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoundDTO {

    private Long id;

    // private TournamentResponseDTO tournament;

    private List<MatchDTO> matches;

}
