package br.com.fabrica.eventos.dto;

import java.time.LocalDateTime;

public record EventResponse(
        String message,
        String eventId,
        String status,
        LocalDateTime receivedAt
) {}
