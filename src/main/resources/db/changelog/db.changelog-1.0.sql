CREATE TABLE IF NOT EXISTS flashcard (
    card_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    lang_word VARCHAR(30) NOT NULL,
    native_word VARCHAR(30) NOT NULL,
    attempt SMALLINT DEFAULT 0,
    isFailed BOOLEAN DEFAULT FALSE
);