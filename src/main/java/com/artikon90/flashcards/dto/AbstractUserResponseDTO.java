package com.artikon90.flashcards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractUserResponseDTO {
    private String status;
    private String context;
}
