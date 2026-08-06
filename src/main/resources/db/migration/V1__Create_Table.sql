CREATE TABLE IF NOT EXISTS teams (
                                     id BIGSERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(10) NOT NULL,
    logo_url VARCHAR(500),
    city VARCHAR(255) NOT NULL,
    founded_year INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX IF NOT EXISTS idx_teams_name ON teams(name);
CREATE INDEX IF NOT EXISTS idx_teams_abbreviation ON teams(abbreviation);
CREATE INDEX IF NOT EXISTS idx_teams_city ON teams(city);