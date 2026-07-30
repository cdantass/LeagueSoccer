package com.cdantas.league.Team.Dto;

import com.cdantas.league.Team.Entity.Team;

public record TeamList(Long id, String name, String abbreviation, String city, int foundedYear) {

    public TeamList(Team team){
        this(team.getId(), team.getName(), team.getAbbreviation(), team.getCity(), team.getFoundedYear());
    }
}