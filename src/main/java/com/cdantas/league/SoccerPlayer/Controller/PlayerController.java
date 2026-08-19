package com.cdantas.league.SoccerPlayer.Controller;

import com.cdantas.league.SoccerPlayer.Dto.PlayerDetail;
import com.cdantas.league.SoccerPlayer.Dto.PlayerList;
import com.cdantas.league.SoccerPlayer.Dto.PlayerRegister;
import com.cdantas.league.SoccerPlayer.Dto.PlayerUpdate;
import com.cdantas.league.SoccerPlayer.Service.SoccerPlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final SoccerPlayerService soccerPlayerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDetail register(@Valid @RequestBody PlayerRegister playerRegister) {
        return soccerPlayerService.register(playerRegister);
    }

    @GetMapping
    public Page<PlayerList> list(Pageable pageable) {
        return soccerPlayerService.list(pageable);
    }

    @GetMapping("/{id}")
    public PlayerDetail detail(@PathVariable Long id) {
        return soccerPlayerService.detail(id);
    }

    @PutMapping("/{id}")
    public PlayerDetail update(@PathVariable Long id, @Valid @RequestBody PlayerUpdate playerUpdate) {
        return soccerPlayerService.update(id, playerUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        soccerPlayerService.delete(id);
    }
}