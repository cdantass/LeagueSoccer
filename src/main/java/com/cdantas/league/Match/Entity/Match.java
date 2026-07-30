package com.cdantas.league.Match.Entity;

import com.cdantas.league.Team.Entity.Team;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "matchs")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(name = "match_date")
    private Date matchDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "home_score")
    private int homeScore;

    @Column(name = "away_score")
    private int awayScore;

    private Long venue;
}