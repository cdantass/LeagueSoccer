CREATE TABLE IF NOT EXISTS match_events (
                                            id BIGSERIAL PRIMARY KEY,
                                            match_id BIGINT NOT NULL,
                                            type VARCHAR(20) NOT NULL,
    minute INTEGER,
    team_id BIGINT,
    player_id BIGINT,
    description VARCHAR(500),
    created_at DATE DEFAULT CURRENT_DATE,
    CONSTRAINT fk_event_match FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE,
    CONSTRAINT fk_event_team FOREIGN KEY (team_id) REFERENCES teams(id),
    CONSTRAINT fk_event_player FOREIGN KEY (player_id) REFERENCES soccer_players(id)
    );

CREATE INDEX IF NOT EXISTS idx_events_match_id ON match_events(match_id);
CREATE INDEX IF NOT EXISTS idx_events_player_id ON match_events(player_id);
CREATE INDEX IF NOT EXISTS idx_events_type ON match_events(type);