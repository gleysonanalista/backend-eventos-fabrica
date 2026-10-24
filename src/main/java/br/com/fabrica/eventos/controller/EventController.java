package br.com.fabrica.eventos.controller;

import br.com.fabrica.eventos.dto.EventRequest;
import br.com.fabrica.eventos.dto.EventResponse;
import br.com.fabrica.eventos.entity.Event;
import br.com.fabrica.eventos.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> receive(
            @Valid @RequestBody EventRequest request) {

        EventResponse response = eventService.receive(request);

        return ResponseEntity
                .created(URI.create("/api/events/" + response.eventId()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<Event>> listAllEvents() {
        return ResponseEntity.ok(eventService.listAllEvents());
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> delete(@PathVariable String eventId) {
        eventService.deleteByEventId(eventId);
        return ResponseEntity.noContent().build();
    }
}
