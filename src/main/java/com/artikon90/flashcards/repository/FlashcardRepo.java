package com.artikon90.flashcards.repository;

import com.artikon90.flashcards.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepo extends JpaRepository<Flashcard, Long> {

    boolean existsByNativeWordAndLang(String nativeWord, String lang);

}
