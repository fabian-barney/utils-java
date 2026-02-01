# MOCKITO

Guidance for Mockito usage.

## Defaults
- Use Mockito for behavior isolation in unit tests.
- Prefer constructor injection to make mocking simple.

## Practices
- Mock external dependencies, not value objects.
- Verify interactions sparingly; focus on behavior and output.
- Avoid over-mocking; it can make tests brittle.
