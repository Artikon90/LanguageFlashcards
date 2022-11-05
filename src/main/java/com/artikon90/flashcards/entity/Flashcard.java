package com.artikon90.flashcards.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "flashcard")
public class Flashcard {

    public static final Flashcard ERROR = new Flashcard(-1, "", "");

    public Flashcard(String lang, String nativeWord) {
        this.lang = lang;
        this.nativeWord = nativeWord;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    @Setter(AccessLevel.NONE)
    private long card_id;

    @Column(name = "lang_word")
    private String lang;

    @Column(name = "native_word")
    private String nativeWord;

}
