package com.cdantas.league.Match.Dto;

import com.cdantas.league.Match.Entity.Status;
import com.cdantas.league.Team.Entity.Team;

import java.util.Date;

public record MatchRegister(Team homeTeam, Team awayTeam, Date match_date, Status status, int homeScore, int awayScore, Long venue) {
}
