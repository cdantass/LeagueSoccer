package com.cdantas.league.Match.Controller;

import com.cdantas.league.Match.Dto.MatchDetail;
import com.cdantas.league.Match.Dto.MatchList;
import com.cdantas.league.Match.Dto.MatchRegister;
import com.cdantas.league.Match.Dto.MatchUpdate;
import com.cdantas.league.Match.Service.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchDetail register(@Valid @RequestBody MatchRegister matchRegister) {
        return matchService.register(matchRegister);
    }

    @GetMapping
    public Page<MatchList> list(Pageable pageable) {
        return matchService.list(pageable);
    }

    @GetMapping("/{id}")
    public MatchDetail detail(@PathVariable Long id) {
        return matchService.detail(id);
    }

    @PutMapping("/{id}")
    public MatchDetail update(@PathVariable Long id, @Valid @RequestBody MatchUpdate matchUpdate) {
        return matchService.update(id, matchUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        matchService.delete(id);
    }
}