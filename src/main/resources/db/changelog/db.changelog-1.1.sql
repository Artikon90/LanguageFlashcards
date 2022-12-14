CREATE TABLE IF NOT EXISTS failed_word (
    fail_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    card_id BIGINT REFERENCES flashcard(card_id),
    success_counter SMALLINT
);
CREATE TABLE IF NOT EXISTS twice_failed_word (
    fail_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    card_id BIGINT REFERENCES flashcard(card_id),
    success_counter SMALLINT
);
