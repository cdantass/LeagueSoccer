package com.cdantas.league.SoccerPlayer.Entity;

import com.cdantas.league.Team.Entity.Team;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "soccerPlayers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class SoccerPlayer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String position;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

}