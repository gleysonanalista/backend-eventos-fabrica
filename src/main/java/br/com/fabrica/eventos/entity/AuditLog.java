package br.com.fabrica.eventos.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_audit")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false, length = 100)
    private String eventId;

    @Column(nullable = false, length = 100)
    private String action;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected AuditLog() {}

    public AuditLog(String eventId, String action, LocalDateTime createdAt) {
        this.eventId = eventId;
        this.action = action;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getEventId() { return eventId; }
    public String getAction() { return action; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
