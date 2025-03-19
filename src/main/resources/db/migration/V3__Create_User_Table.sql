CREATE TABLE users (
    id SERIAL PRIMARY KEY,                          -- Auto-incrementing ID, unique for each user
    username VARCHAR(50) UNIQUE NOT NULL,            -- Username must be unique and cannot be NULL
    password VARCHAR(255) NOT NULL,                  -- Password cannot be NULL
    email VARCHAR(100) UNIQUE NOT NULL,              -- Email must be unique and cannot be NULL
    role VARCHAR(20) NOT NULL                        -- Role cannot be NULL
);