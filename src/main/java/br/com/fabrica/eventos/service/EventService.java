package br.com.fabrica.eventos.service;

import br.com.fabrica.eventos.dto.EventRequest;
import br.com.fabrica.eventos.dto.EventResponse;
import br.com.fabrica.eventos.entity.AuditLog;
import br.com.fabrica.eventos.entity.Event;
import br.com.fabrica.eventos.enums.EventType;
import br.com.fabrica.eventos.exception.DuplicateEventException;
import br.com.fabrica.eventos.exception.EventNotFoundException;
import br.com.fabrica.eventos.repository.AuditLogRepository;
import br.com.fabrica.eventos.repository.EventRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AuditLogRepository auditLogRepository;

    public EventService(EventRepository eventRepository, AuditLogRepository auditLogRepository) {
        this.eventRepository = eventRepository;
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional
    public EventResponse receive(EventRequest request) {
        if (eventRepository.existsByEventId(request.eventId())) {
            throw new DuplicateEventException(request.eventId());
        }

        LocalDateTime now = LocalDateTime.now();
        Event event = new Event(
                request.eventId(),
                request.timestamp(),
                request.type(),
                request.description(),
                now
        );

        try {
            eventRepository.saveAndFlush(event);
        } catch (DataIntegrityViolationException ex) {
            // A restrição UNIQUE também protege contra duas requisições simultâneas.
            throw new DuplicateEventException(request.eventId());
        }

        if (request.type() == EventType.QUALITY) {
            auditLogRepository.save(new AuditLog(
                    request.eventId(),
                    "EVENTO_QUALITY_RECEBIDO",
                    now
            ));
        }

        return new EventResponse(
                request.type() == EventType.QUALITY
                        ? "Evento de qualidade registrado e auditado com sucesso"
                        : "Evento de produção registrado com sucesso",
                request.eventId(),
                "PROCESSED",
                now
        );
    }

        public List<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public void deleteByEventId(String eventId) {
        if (!eventRepository.existsByEventId(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        eventRepository.deleteByEventId(eventId);
    }
}
