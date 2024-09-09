CREATE TABLE IF NOT EXISTS users_table(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(33) NOT NULL,
    registered_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS user_linked_socials_table(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    social_type VARCHAR(255) NOT NULL,
    verification_data VARCHAR(255) UNIQUE NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users_table(id)
);