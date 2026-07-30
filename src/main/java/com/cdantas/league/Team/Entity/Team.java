package com.cdantas.league.Team.Entity;

import com.cdantas.league.Match.Entity.Match;
import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;
import com.cdantas.league.Team.Dto.TeamRegister;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String abbreviation;

    @Column(name = "logo_url")
    private String logoUrl;

    private String city;

    @Column(name = "founded_year")
    private int foundedYear;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SoccerPlayer> players = new ArrayList<>();

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Match> homeMatches = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Match> awayMatches = new ArrayList<>();

    public Team(TeamRegister teamRegister){
        this.name = teamRegister.name();
        this.abbreviation = teamRegister.abbreviation();
        this.city = teamRegister.city();
        this.foundedYear = teamRegister.foundedYear();
    }
}