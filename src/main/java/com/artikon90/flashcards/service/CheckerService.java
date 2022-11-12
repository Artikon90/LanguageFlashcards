package com.artikon90.flashcards.service;

import com.artikon90.flashcards.dto.AbstractUserResponseDTO;
import com.artikon90.flashcards.dto.CheckCorrectnessDTO;
import com.artikon90.flashcards.dto.SuccessfulResponseDTO;
import com.artikon90.flashcards.entity.FailedWord;
import com.artikon90.flashcards.repository.FailedWordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckerService {
    private final FlashcardService flashService;
    private final FailedWordRepo failRepo;

    @Autowired
    public CheckerService(FlashcardService flashService, FailedWordRepo failRepo) {
        this.flashService = flashService;
        this.failRepo = failRepo;
    }

    public boolean isCorrectAttempt(CheckCorrectnessDTO dto) {
        var card = flashService.getCard(dto.getCardId());
        return card.getNativeWord().equals(dto.getAttemptVariable());
    }

//TODO: нужно отрефакторить этот треш
    public AbstractUserResponseDTO checkCorrectness(CheckCorrectnessDTO dto) {
        var isCorrect = this.isCorrectAttempt(dto);
        var card = flashService.getCard(dto.getCardId());
        if (isCorrect) {
            boolean isChange = false;
            if (card.getAttempt() > 0) {
                isChange = true;
                card.setAttempt((short) 0);
            }
            if (card.isFailed()) {
                var failInfo = failRepo.getFailedWordByCardId(card);
                if (failInfo.getSuccessCounter() >= 2) {
                    failRepo.delete(failInfo);
                    isChange = true;
                    card.setFailed(false);
                } else {
                    failInfo.setSuccessCounter((short)(failInfo.getSuccessCounter() + 1));
                    failRepo.save(failInfo);
                }
            }
            if (isChange)
                flashService.updateCard(card);
            return new SuccessfulResponseDTO(AbstractUserResponseDTO.Status.OK.get(),
                    "next");
        } else {
            String message;
            if (card.getAttempt() < 2) {
                card.setAttempt((short)(card.getAttempt() + 1));
                message = "fail";
            } else {
                card.setAttempt((short) 0);
                card.setFailed(true);
                failRepo.save(new FailedWord(card, (short)0));
                message = "next";
            }
            flashService.updateCard(card);
            return new SuccessfulResponseDTO(AbstractUserResponseDTO.Status.OK.get(),
                    message);
        }
    }
}
