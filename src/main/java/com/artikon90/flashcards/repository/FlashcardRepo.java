package com.artikon90.flashcards.repository;

import com.artikon90.flashcards.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepo extends JpaRepository<Flashcard, Long> {

    boolean existsByLang_wordAndNative_word(String lang_word, String native_word);

}
