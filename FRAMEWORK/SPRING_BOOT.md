# SPRING_BOOT

Guidance for AI agents implementing and reviewing Spring Boot applications.

## Scope
- Define Spring Boot-specific defaults for layering, configuration, and runtime
  behavior.
- Apply this file to Spring Boot service/application code and review.

## Semantic Dependencies
- Inherit Java baseline from `LANGUAGE/JAVA/JAVA.md` and
  `LANGUAGE/JAVA/EFFECTIVE_JAVA.md`.
- Inherit architecture constraints from `ARCHITECTURE/CLEAN_ARCHITECTURE.md`
  and `ARCHITECTURE/REST.md` where relevant.
- Inherit cross-cutting constraints from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.

## Defaults
- Prefer constructor injection (for example with Lombok
  `@RequiredArgsConstructor`), avoid field injection.
- Keep controllers thin; move orchestration/business rules into services or
  use-case classes.
- Keep persistence logic in repositories/adapters, not controllers.
- Prefer explicit DTOs for API boundaries.
- Use `@ConfigurationProperties` for structured config over scattered
  `@Value` usage.

## Configuration and Profiles
- Keep configuration keys stable and documented.
- Use profile/environment separation intentionally.
- Keep defaults safe for local/dev and override via environment for production.
- Validate critical configuration at startup.

## Web and API Layer
- Validate request payloads with Bean Validation.
- Keep exception-to-response mapping centralized (`@ControllerAdvice`).
- Use consistent API error payload shapes with trace correlation.
- Avoid exposing internal exception details to clients.

## Transactions and Persistence
- Keep transaction boundaries explicit and use-case aligned.
- Avoid long transactions with remote/network calls inside.
- Choose JPA or jOOQ intentionally per query complexity.
- Avoid Open Session in View reliance for business-critical flows.

## Asynchrony and Scheduling
- Use async/scheduling only with explicit thread pool configuration.
- Keep background job idempotency and retry policy explicit.
- Propagate correlation context where observability requires it.

## Observability
- Use structured logging with correlation IDs.
- Expose health/readiness checks meaningfully.
- Emit metrics for key business and dependency operations.
- Keep log/metric cardinality controlled.

## Security Baseline
- Enforce authentication/authorization at endpoint and service boundaries.
- Keep secrets out of code/config files; use secret management paths.
- Validate and sanitize external input.
- Apply least privilege for outbound clients and data access.

## High-Risk Pitfalls
1. Business logic in controllers.
2. Field injection and hidden dependencies.
3. Configuration sprawl via many ad-hoc `@Value` strings.
4. Leaky exceptions returning stack traces to clients.
5. Open-ended transactions across remote calls.
6. Silent async failures in scheduled/background jobs.
7. Overuse of global state/static caches without lifecycle control.

## Do / Don't Examples
### 1. Injection Style
```text
Don't: @Autowired field injection.
Do:    constructor injection with final dependencies.
```

### 2. Layering
```text
Don't: controller directly manipulates EntityManager.
Do:    controller -> service/use-case -> repository adapter.
```

### 3. Config Management
```text
Don't: scattered @Value("${x.y}") across many classes.
Do:    typed @ConfigurationProperties with validation.
```

## Code Review Checklist for Spring Boot
- Are dependencies injected via constructors?
- Are controller/service/repository boundaries respected?
- Are validation and error mapping consistent and centralized?
- Are transactions scoped and safe?
- Is configuration typed, validated, and environment-safe?
- Are observability and correlation patterns applied?
- Are security boundaries enforced and secrets handled correctly?

## Testing Guidance
- Add unit tests for service/use-case logic.
- Add slice tests for web/persistence layers where useful.
- Add integration tests for transaction and persistence behavior.
- Add security tests for auth/authz and error exposure.
- Add tests for configuration binding/validation of critical properties.

## Override Notes
- Library docs (JPA, jOOQ, Resilience4j, etc.) may add stricter rules for
  specific integrations, but Spring Boot layering, configuration, and boundary
  constraints here remain mandatory.
