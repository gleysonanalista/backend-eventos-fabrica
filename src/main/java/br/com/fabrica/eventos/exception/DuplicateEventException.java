package br.com.fabrica.eventos.exception;

public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException(String eventId) {
        super("O evento com ID '" + eventId + "' já foi processado. Não é permitido cadastrar duplicados." );
    }
}
