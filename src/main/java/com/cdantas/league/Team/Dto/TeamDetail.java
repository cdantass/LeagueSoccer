package com.cdantas.league.Team.Dto;

import com.cdantas.league.Team.Entity.Team;

public record TeamDetail(Long id, String name, String abbreviation, String logoUrl, String city, int foundedYear) {

    public TeamDetail(Team team){
        this(team.getId(), team.getName(), team.getAbbreviation(), team.getLogoUrl(), team.getCity() , team.getFoundedYear());
    }
}