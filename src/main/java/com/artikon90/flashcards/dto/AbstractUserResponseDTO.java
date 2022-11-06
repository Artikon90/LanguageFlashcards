package com.artikon90.flashcards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractUserResponseDTO {
    private String status;
    private String content;
    public enum Status {
        OK("OK"),
        ERROR("ERROR"),
        INVALID("INVALID");

        private final String value;
        Status(String value) {
            this.value = value;
        }
        public String get() {
            return value;
        }
    }
}
