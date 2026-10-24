# Testes sugeridos

Cenários importantes para automatizar com JUnit 5, MockMvc e um banco H2 de teste:
1. PRODUCTION novo -> HTTP 201, um evento, nenhuma auditoria.
2. QUALITY novo -> HTTP 201, um evento e uma auditoria.
3. Reenvio do mesmo ID -> HTTP 409, sem segundo evento/auditoria.
4. Campos obrigatórios ausentes -> HTTP 400.
5. Tipo fora do enum -> HTTP 400.
6. Erro ao persistir auditoria -> transação deve reverter o registro do evento.
