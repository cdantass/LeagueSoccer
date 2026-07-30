package com.cdantas.league.SoccerPlayer.Dto;

import com.cdantas.league.Team.Entity.Team;

public record PlayerUpdate(String position, Integer jersey_number, Long team_id) {
}
