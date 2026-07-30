package com.cdantas.league.SoccerPlayer.Dto;

import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;
import com.cdantas.league.Team.Entity.Team;

public record PlayerList(Long id,String name, String position, Long team_id) {

    public PlayerList(SoccerPlayer soccerPlayer){
        this(soccerPlayer.getId(), soccerPlayer.getName(), soccerPlayer.getPosition(), soccerPlayer.getTeam_id().getId());
    }
}
