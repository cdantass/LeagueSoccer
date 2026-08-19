package com.cdantas.league.MatchEvent.Repository;

import com.cdantas.league.MatchEvent.Entity.MatchEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchEventRepository extends JpaRepository<MatchEvent, Long> {
    List<MatchEvent> findByMatchIdOrderByMinuteAsc(Long matchId);
}