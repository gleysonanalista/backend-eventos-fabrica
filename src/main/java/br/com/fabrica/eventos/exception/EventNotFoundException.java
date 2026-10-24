package br.com.fabrica.eventos.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String eventId) {
        super("Evento " + eventId + " não encontrado.");
    }
}