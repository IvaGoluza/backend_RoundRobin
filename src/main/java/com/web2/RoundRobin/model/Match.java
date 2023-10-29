package com.web2.RoundRobin.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MATCH")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String result;

    @OneToOne
    @JoinColumn
    private Team team1;

    @OneToOne
    @JoinColumn
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

}
