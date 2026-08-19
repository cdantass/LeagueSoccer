package com.cdantas.league.Team.Service;

import com.cdantas.league.Team.Dto.TeamDetail;
import com.cdantas.league.Team.Dto.TeamList;
import com.cdantas.league.Team.Dto.TeamRegister;
import com.cdantas.league.Team.Entity.Team;
import com.cdantas.league.Team.Repository.TeamRepository;
import com.cdantas.league.infra.exception.TeamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public TeamDetail register(TeamRegister teamRegister) {
        var team = new Team(teamRegister);
        teamRepository.save(team);
        return new TeamDetail(team);
    }

    public Page<TeamList> list(Pageable pageable) {
        return teamRepository.findAll(pageable).map(TeamList::new);
    }

    public TeamDetail detail(Long id) {
        var team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFound("Time não encontrado: " + id));
        return new TeamDetail(team);
    }

    @Transactional
    public TeamDetail update(Long id, TeamRegister teamRegister) {
        var team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFound("Time não encontrado: " + id));

        team.setName(teamRegister.name());
        team.setAbbreviation(teamRegister.abbreviation());
        team.setCity(teamRegister.city());
        team.setFoundedYear(teamRegister.foundedYear());

        return new TeamDetail(team);
    }

    @Transactional
    public void delete(Long id) {
        var team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFound("Time não encontrado: " + id));
        teamRepository.delete(team);
    }
}