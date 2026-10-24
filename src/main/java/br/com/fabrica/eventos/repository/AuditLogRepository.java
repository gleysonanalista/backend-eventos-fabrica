package br.com.fabrica.eventos.repository;

import br.com.fabrica.eventos.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEventId(String eventId);
}
