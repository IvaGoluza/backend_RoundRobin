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

    @Column
    private Long team1;

    @Column
    private Long team2;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

}
