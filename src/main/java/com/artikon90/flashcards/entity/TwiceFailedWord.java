package com.artikon90.flashcards.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "twice_failed_word")
public class TwiceFailedWord {

    public TwiceFailedWord(Flashcard cardId, short successCounter) {
        this.cardId = cardId;
        this.successCounter = successCounter;
    }

    @Id
    @Column(name = "fail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long failId;

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
    private Flashcard cardId;

    @Column(name = "success_counter")
    @Setter
    private short successCounter;
}
