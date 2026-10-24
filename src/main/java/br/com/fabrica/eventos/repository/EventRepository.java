package br.com.fabrica.eventos.repository;

import br.com.fabrica.eventos.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByEventId(String eventId);
    void deleteByEventId(String eventId);
}
