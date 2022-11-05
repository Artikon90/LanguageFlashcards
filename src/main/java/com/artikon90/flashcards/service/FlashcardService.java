package com.artikon90.flashcards.service;

import com.artikon90.flashcards.entity.Flashcard;
import com.artikon90.flashcards.repository.FlashcardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FlashcardService {

    private final FlashcardRepo repository;

    @Autowired
    public FlashcardService(FlashcardRepo repository) {
        this.repository = repository;
    }

    public Flashcard getCard(long id) {
        return repository.findById(id).orElse(Flashcard.ERROR);
    }

    public boolean saveCard(Flashcard card) {
        var savedEntity = repository.save(card);
        return repository.existsById(savedEntity.getCard_id());
    }

    public List<Flashcard> getAllCards() {
        return repository.findAll();
    }

    public boolean isAlreadyExists(String langWord, String nativeWord) {
        return repository.existsByNativeWordAndLang(nativeWord, langWord);
    }

    @Transactional
    public boolean updateCard(Flashcard updatedCard) {
        var oldCard = this.getCard(updatedCard.getCard_id());
        // проверяю, что полученный энтити не -1, т.е полученный объект не ERROR
        if (repository.existsById(updatedCard.getCard_id()) && oldCard.getCard_id() > 0) {
            oldCard.setLang(updatedCard.getLang());
            oldCard.setNativeWord(updatedCard.getNativeWord());
            return saveCard(updatedCard);
        } else {
            return false;
        }
    }
}
