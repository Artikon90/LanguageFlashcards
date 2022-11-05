package com.artikon90.flashcards.dto;

public class ErrorResponseDTO extends AbstractUserResponseDTO {
    public ErrorResponseDTO(String status, String context) {
        super(status, context);
    }
}
