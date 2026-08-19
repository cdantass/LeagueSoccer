package com.cdantas.league.Match.Repository;

import com.cdantas.league.Match.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}