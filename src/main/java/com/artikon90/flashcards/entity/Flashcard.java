package com.artikon90.flashcards.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "flashcard")
public class Flashcard {

    public static final Flashcard ERROR = new Flashcard(-1, "", "");

    @Id
    @Column(name = "card_id")
    @Setter(AccessLevel.NONE)
    private long card_id;

    @Column(name = "lang_word")
    private String lang_word;

    @Column(name = "native_word")
    private String native_word;

}
