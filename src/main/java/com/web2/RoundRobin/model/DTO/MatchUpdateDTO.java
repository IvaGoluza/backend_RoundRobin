package com.web2.RoundRobin.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchUpdateDTO {

    private Long matchId;

    private Long tourId;

    private String score;
}
