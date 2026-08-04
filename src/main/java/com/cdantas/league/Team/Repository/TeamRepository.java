package com.cdantas.league.Team.Repository;

import com.cdantas.league.Team.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Override
    Optional<Team> findById(Long teamId);
}
