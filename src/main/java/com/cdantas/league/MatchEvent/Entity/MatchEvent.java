package com.cdantas.league.MatchEvent.Entity;

import com.cdantas.league.Match.Entity.Match;
import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;
import com.cdantas.league.Team.Entity.Team;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "match_events")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class MatchEvent {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Match match;

    @Enumerated(EnumType.STRING)
    private MatchEventType type;

    private Integer minute;

    @ManyToOne
    private Team team;

    @ManyToOne
    private SoccerPlayer player;

    private String description;

    private Date createdAt;
}