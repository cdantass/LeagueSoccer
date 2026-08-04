package com.cdantas.league.SoccerPlayer.Entity;

import com.cdantas.league.SoccerPlayer.Dto.PlayerRegister;
import com.cdantas.league.SoccerPlayer.Dto.PlayerUpdate;
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

    private boolean active;

    public SoccerPlayer(PlayerRegister playerRegister, Team team) {
        this.name = playerRegister.name();
        this.position = playerRegister.position();
        this.jerseyNumber = playerRegister.jerseyNumber();
        this.age = playerRegister.age();
        this.team = team;
    }

    public void update(PlayerUpdate playerUpdate, Team team) {
        if (playerUpdate.position() != null) {
            this.position = playerUpdate.position();
        }
        if (playerUpdate.jerseyNumber() != null) {
            this.jerseyNumber = playerUpdate.jerseyNumber();
        }
        if (team != null) {
            this.team = team;
        }
    }

    public void delete() {
        this.active = false;
    }
}