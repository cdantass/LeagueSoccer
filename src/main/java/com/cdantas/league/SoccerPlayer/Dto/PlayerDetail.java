package com.cdantas.league.SoccerPlayer.Dto;

import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;
import com.cdantas.league.Team.Entity.Team;

public record PlayerDetail(Long id, String name, String position, Integer jersey_number, Long team_id) {

    public PlayerDetail(SoccerPlayer soccerPlayer){
        this(soccerPlayer.getId(), soccerPlayer.getName(), soccerPlayer.getPosition(), soccerPlayer.getJersey_number(), soccerPlayer.getTeam_id().getId());
    }
}