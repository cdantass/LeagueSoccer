package com.cdantas.league.MatchEvent.Controller;

import com.cdantas.league.MatchEvent.Dto.MatchEventDetail;
import com.cdantas.league.MatchEvent.Dto.MatchEventRegister;
import com.cdantas.league.MatchEvent.Service.MatchEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches/{matchId}/events")
@RequiredArgsConstructor
public class MatchEventController {

    private final MatchEventService matchEventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchEventDetail register(@PathVariable Long matchId, @Valid @RequestBody MatchEventRegister dto) {
        return matchEventService.register(matchId, dto);
    }

    @GetMapping
    public List<MatchEventDetail> listByMatch(@PathVariable Long matchId) {
        return matchEventService.listByMatch(matchId);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long matchId, @PathVariable Long eventId) {
        matchEventService.delete(eventId);
    }
}