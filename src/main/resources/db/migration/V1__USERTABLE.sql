CREATE TABLE IF NOT EXISTS users (
    id  BIGSERIAL NOT NULL,  
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

