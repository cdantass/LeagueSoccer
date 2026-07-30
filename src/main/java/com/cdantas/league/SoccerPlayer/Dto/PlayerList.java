package com.cdantas.league.SoccerPlayer.Dto;

import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;

public record PlayerList(Long id,String name, String position, Long teamId) {

    public PlayerList(SoccerPlayer soccerPlayer){
        this(soccerPlayer.getId(), soccerPlayer.getName(), soccerPlayer.getPosition(), soccerPlayer.getTeam().getId());
    }
}