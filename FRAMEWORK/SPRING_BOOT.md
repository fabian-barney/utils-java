# SPRING_BOOT

Guidance for Spring Boot projects.

## Defaults
- Follow Spring Boot conventions and autoconfiguration.
- Prefer constructor injection with Lombok `@RequiredArgsConstructor`.
- Avoid field injection.

## Configuration
- Use `@ConfigurationProperties` for structured config.
- Keep `application.yml` organized by feature.
- Use profiles for environment differences.

## Web and API
- Keep controllers thin; move logic to services.
- Use DTOs for API boundaries.
- Validate inputs with Bean Validation.

## Persistence
- Prefer JPA for CRUD and jOOQ for complex queries.
- Keep transactions explicit where it matters.

## Testing
- Use slice tests when possible.
- Prefer testcontainers for integration testing when DB behavior matters.
