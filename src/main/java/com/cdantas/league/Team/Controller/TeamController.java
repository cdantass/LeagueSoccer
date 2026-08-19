package com.cdantas.league.Team.Controller;

import com.cdantas.league.Team.Dto.TeamDetail;
import com.cdantas.league.Team.Dto.TeamList;
import com.cdantas.league.Team.Dto.TeamRegister;
import com.cdantas.league.Team.Service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDetail register(@Valid @RequestBody TeamRegister teamRegister) {
        return teamService.register(teamRegister);
    }

    @GetMapping
    public Page<TeamList> list(Pageable pageable) {
        return teamService.list(pageable);
    }

    @GetMapping("/{id}")
    public TeamDetail detail(@PathVariable Long id) {
        return teamService.detail(id);
    }

    @PutMapping("/{id}")
    public TeamDetail update(@PathVariable Long id, @Valid @RequestBody TeamRegister teamRegister) {
        return teamService.update(id, teamRegister);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}