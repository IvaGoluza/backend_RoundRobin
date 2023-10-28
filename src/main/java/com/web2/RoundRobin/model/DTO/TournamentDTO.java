package com.web2.RoundRobin.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TournamentDTO {

    private String tourName;

    private String teams;

    private String scoreSystem;

    private Long userId;
}
