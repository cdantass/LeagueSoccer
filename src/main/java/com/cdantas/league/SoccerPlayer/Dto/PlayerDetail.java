package com.cdantas.league.SoccerPlayer.Dto;

import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;

public record PlayerDetail(Long id, String name, String position, Integer jerseyNumber, Long teamId) {

    public PlayerDetail(SoccerPlayer soccerPlayer){
        this(soccerPlayer.getId(), soccerPlayer.getName(), soccerPlayer.getPosition(), soccerPlayer.getJerseyNumber(), soccerPlayer.getTeam().getId());
    }
}