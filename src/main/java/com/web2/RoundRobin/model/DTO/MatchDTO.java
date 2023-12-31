package com.web2.RoundRobin.model.DTO;

import com.web2.RoundRobin.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchDTO {

    private Long id;

    private String result;

    private TeamDTO team1;

    private TeamDTO team2;

    // private RoundDTO round;
}
