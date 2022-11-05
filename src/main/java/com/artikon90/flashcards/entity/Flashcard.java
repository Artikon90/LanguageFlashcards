package com.artikon90.flashcards.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "flashcard")
public class Flashcard {

    @Id
    @Column(name = "card_id")
    private long card_id;

    @Column(name = "lang_word")
    private String lang_word;

    @Column(name = "native_word")
    private String native_word;

}
