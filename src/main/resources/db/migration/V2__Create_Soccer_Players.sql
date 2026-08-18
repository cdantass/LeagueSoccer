CREATE TABLE IF NOT EXISTS soccer_players (
                                              id BIGSERIAL PRIMARY KEY,
                                              name VARCHAR(255) NOT NULL,
    position VARCHAR(50) NOT NULL,
    jersey_number INTEGER,
    age INTEGER NOT NULL,
    team_id BIGINT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_player_team FOREIGN KEY (team_id) REFERENCES teams(id)
    );

CREATE INDEX IF NOT EXISTS idx_players_team_id ON soccer_players(team_id);
CREATE INDEX IF NOT EXISTS idx_players_name ON soccer_players(name);
CREATE UNIQUE INDEX IF NOT EXISTS uq_players_team_jersey ON soccer_players(team_id, jersey_number) WHERE jersey_number IS NOT NULL;