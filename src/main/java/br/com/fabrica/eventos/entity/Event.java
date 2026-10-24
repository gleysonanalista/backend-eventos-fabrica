package br.com.fabrica.eventos.entity;

import br.com.fabrica.eventos.enums.EventType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "industrial_event", uniqueConstraints =
        @UniqueConstraint(name = "uk_industrial_event_event_id", columnNames = "event_id"))
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false, unique = true, length = 100)
    private String eventId;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EventType type;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime receivedAt;

    protected Event() {}

    public Event(String eventId, LocalDateTime timestamp, EventType type,
                 String description, LocalDateTime receivedAt) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.type = type;
        this.description = description;
        this.receivedAt = receivedAt;
    }

    public Long getId() { return id; }
    public String getEventId() { return eventId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public EventType getType() { return type; }
    public String getDescription() { return description; }
    public LocalDateTime getReceivedAt() { return receivedAt; }
}
