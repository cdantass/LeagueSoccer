package com.cdantas.league.SoccerPlayer.Dto;

import com.cdantas.league.Team.Entity.Team;

public record PlayerRegister(String name, String position, Integer jersey_number, int age, Long team_id) {
}
