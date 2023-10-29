package com.web2.RoundRobin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TOURNAMENT")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String UUID;

    @Column(nullable = false)
    private String tourName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scoreSystem_id", referencedColumnName = "id")
    private ScoreSystem scoreSystem;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Round> rounds;

}
