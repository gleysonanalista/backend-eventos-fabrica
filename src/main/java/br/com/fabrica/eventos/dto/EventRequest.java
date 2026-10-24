package br.com.fabrica.eventos.dto;

import br.com.fabrica.eventos.enums.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventRequest(
        @NotBlank(message = "O ID do evento é obrigatório")
        @Size(max = 100, message = "O ID deve ter no máximo 100 caracteres")
        String eventId,

        @NotNull(message = "A data/hora do evento é obrigatória")
        LocalDateTime timestamp,

        @NotNull(message = "O tipo do evento é obrigatório")
        EventType type,

        @NotBlank(message = "A descrição do evento é obrigatória")
        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
        String description
) {}
