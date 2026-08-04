package com.cdantas.league.SoccerPlayer.Repository;

import com.cdantas.league.SoccerPlayer.Entity.SoccerPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<SoccerPlayer, Long> {
}
