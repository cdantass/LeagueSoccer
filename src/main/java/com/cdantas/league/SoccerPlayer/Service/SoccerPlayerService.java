package com.cdantas.league.SoccerPlayer.Service;

import com.cdantas.league.SoccerPlayer.Dto.PlayerDetail;
import com.cdantas.league.SoccerPlayer.Dto.PlayerList;
import com.cdantas.league.SoccerPlayer.Dto.PlayerRegister;
import com.cdantas.league.SoccerPlayer.Dto.PlayerUpdate;
import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;
import com.cdantas.league.SoccerPlayer.Repository.PlayerRepository;
import com.cdantas.league.Team.Repository.TeamRepository;
import com.cdantas.league.infra.exception.PlayerNotFound;
import com.cdantas.league.infra.exception.TeamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SoccerPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public PlayerDetail register(PlayerRegister playerRegister){
        var team = teamRepository.findById(playerRegister.teamId())
                .orElseThrow(() -> new TeamNotFound("Team not found"));

        var player = playerRepository.save(new SoccerPlayer(playerRegister, team));
        return new PlayerDetail(player);
    }

    public Page<PlayerList> list(Pageable pageable){
        return playerRepository.findAll(pageable)
                .map(PlayerList::new);
    }

    public PlayerDetail detail(Long id){
        var player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFound("Player not found"));
        return new PlayerDetail(player);
    }

    @Transactional
    public PlayerDetail update(Long id, PlayerUpdate playerUpdate){
        var player = playerRepository.findById(id)
                .orElseThrow(()-> new PlayerNotFound("Player not found"));

        var team = teamRepository.findById(playerUpdate.teamId())
                .orElseThrow(() -> new TeamNotFound("Team not found"));

        player.update(playerUpdate, team);

        return new PlayerDetail(player);

    }

    @Transactional
    public void delete(Long id){
        var player = playerRepository.findById(id)
                .orElseThrow(()-> new PlayerNotFound("Player not found"));
        playerRepository.delete(player);
    }
}