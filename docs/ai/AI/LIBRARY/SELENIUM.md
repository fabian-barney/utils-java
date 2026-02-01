# SELENIUM

Guidance for Selenium usage.

## Defaults
- Use Selenium for browser-level end-to-end coverage when required.
- Keep tests deterministic; avoid sleeps in favor of explicit waits.

## Structure
- Use page objects or similar abstraction to reduce duplication.
- Keep selectors stable and resilient.

## Performance
- Keep the e2e suite focused on critical paths.
