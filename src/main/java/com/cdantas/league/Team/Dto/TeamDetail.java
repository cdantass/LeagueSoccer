package com.cdantas.league.Team.Dto;

import com.cdantas.league.Team.Entity.Team;

public record TeamDetail(Long id, String name, String abbreviation, String logo_url, String city, int founded_year) {

    public TeamDetail(Team team){
        this(team.getId(), team.getName(), team.getAbbreviation(), team.getLogo_url(), team.getCity() , team.getFounded_year());
    }
}