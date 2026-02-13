# LIBRARY

Library-layer contract for technology-specific API usage and integration
patterns.

## Role in the Ruleset
- LIBRARY docs specialize framework/language rules for specific libraries.
- Library docs inherit cross-cutting, language, and framework constraints before
  applying API-specific guidance.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
LIBRARY includes:
- Library API usage patterns and anti-patterns.
- Library-specific reliability, performance, and testing guardrails.
- Integration boundaries for library behavior in application code.

LIBRARY does not include:
- Generic language conventions/readability constraints.
- Framework lifecycle and rendering/state-management guidance.
- Build/deployment/CI infrastructure behavior.

Those belong in `LANGUAGE/**`, `FRAMEWORK/**`, `BUILD_TOOLS/**`,
`INFRASTRUCTURE/**`, and `CI-CD/**`.

## Specialization Contract
- Library docs may narrow parent rules where library semantics require it.
- Any parent-rule override must be explicit and justified in the library doc.
- Library docs must not weaken inherited security/compliance/test constraints
  without an explicit, reviewed rationale.

## Selection Guidance
- Prefer mature, enterprise-ready libraries with a proven track record.
- Popularity is a strong indicator; widely adopted libraries are more likely to
  be maintained and supported long term.
- Prefer trustworthy stewardship (e.g., Apache Foundation, Linux Foundation,
  major vendors, or well-governed communities) over one-person side projects.
- Avoid adopting very new libraries without evidence of long-term sustainability.
- Favor clear release cadences, published support windows, and strong maintenance.
- Prefer comprehensive feature sets over hype-driven adoption.
- Ensure licenses are compatible with commercial closed-source use and that
  required attribution is provided; see `COMPLIANCE/LICENSES.md`.

## Files
- [GUAVA.md](GUAVA.md) - Google Guava library guidance.
- [JAXB.md](JAXB.md) - JAXB XML binding guidance.
- [JEST.md](JEST.md) - Jest testing guidance.
- [JOOQ.md](JOOQ.md) - jOOQ SQL DSL guidance.
- [JPA.md](JPA.md) - Java Persistence API guidance.
- [JUNIT.md](JUNIT.md) - JUnit testing guidance.
- [KAFKA.md](KAFKA.md) - Apache Kafka usage guidance.
- [LOMBOK.md](LOMBOK.md) - Lombok usage guidance.
- [MAPSTRUCT.md](MAPSTRUCT.md) - MapStruct mapping guidance.
- [MOCKITO.md](MOCKITO.md) - Mockito mocking guidance.
- [PLAYWRIGHT.md](PLAYWRIGHT.md) - Playwright browser testing guidance.
- [RESILIENCE4J.md](RESILIENCE4J.md) - Resilience4j patterns guidance.
- [SELENIUM.md](SELENIUM.md) - Selenium browser testing guidance.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep usage details in the child library documents.
- When adding a new library doc, update this index and align semantic
  dependencies in `CORE/RULE_DEPENDENCY_TREE.md`.
