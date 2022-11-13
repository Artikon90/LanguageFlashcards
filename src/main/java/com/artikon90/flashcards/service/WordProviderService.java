package com.artikon90.flashcards.service;

import com.artikon90.flashcards.entity.Flashcard;
import com.artikon90.flashcards.repository.FailedWordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordProviderService {

    private final FlashcardService flashService;
    private final FailedWordRepo failedRepo;

    @Autowired
    public WordProviderService(FlashcardService flashService, FailedWordRepo failedRepo) {
        this.flashService = flashService;
        this.failedRepo = failedRepo;
    }

    public List<Flashcard> getCardsPool(int countOfCards) {
        var resultList = new ArrayList<Flashcard>();
        failedRepo.findAll().forEach(e ->
            resultList.add(e.getCardId()) // добавляю все зафейленные слова в выборку в любом случае
        );
        if (resultList.size() < countOfCards) {
            getListRandomId(flashService.getCardsCount(), countOfCards - resultList.size(), resultList)
                    .forEach(id -> resultList.add(flashService.getCard(id)));
        }
        return resultList;
    }

    private static List<Long> getListRandomId(long maxValue, int numberOfId, List<Flashcard> usedValue) {
        var res = new ArrayList<Long>();
        var almostUsedId = getListIdFromEntityList(usedValue);
        while (res.size() < numberOfId) {
            long number = (long)(Math.random() * maxValue) + 1;
            if (!res.contains(number) && !almostUsedId.contains(number))
                res.add(number);
        }
        return res;
    }

    private static List<Long> getListIdFromEntityList(List<Flashcard> flashcardList) {
        var list = new ArrayList<Long>();
        flashcardList.forEach(e -> list.add(e.getCard_id()));
        return list;
    }
}
