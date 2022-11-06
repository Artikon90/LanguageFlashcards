package com.artikon90.flashcards.controller.v1;

import com.artikon90.flashcards.dto.AbstractUserResponseDTO;
import com.artikon90.flashcards.dto.SuccessfulResponseDTO;
import com.artikon90.flashcards.entity.Flashcard;
import com.artikon90.flashcards.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flashcard")
public class FlashcardController {

    private final FlashcardService service;

    @Autowired
    public FlashcardController(FlashcardService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Flashcard> getAllCards() {
        return service.getAllCards();
    }

    @GetMapping("/{id}")
    public Flashcard getById(@PathVariable("id") long id) {
        return service.getCard(id);
    }

    @PostMapping("/new")
    public AbstractUserResponseDTO saveNewCard(@RequestBody Flashcard card) {
        return new SuccessfulResponseDTO(AbstractUserResponseDTO.Status.OK.get(),
                String.valueOf(service.saveCard(card)));
    }

    @PatchMapping("/update")
    public AbstractUserResponseDTO updateCard(@RequestBody Flashcard card) {
        return new SuccessfulResponseDTO(AbstractUserResponseDTO.Status.OK.get(),
                String.valueOf(service.updateCard(card)));
    }

    @DeleteMapping("/delete/{id}")
    public AbstractUserResponseDTO deleteCard(@PathVariable long id) {
        return new SuccessfulResponseDTO(AbstractUserResponseDTO.Status.OK.get(),
                String.valueOf(service.deleteCard(id)));
    }
}
