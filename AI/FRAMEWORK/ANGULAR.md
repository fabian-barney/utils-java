# ANGULAR

Guidance for Angular projects.

## Defaults
- Follow Angular style guide conventions.
- Prefer standalone APIs where appropriate.

## Structure
- Keep components small and focused.
- Use feature modules or folder-based boundaries for clarity.
- Avoid logic in templates; move to component or service.

## State and Data
- Keep services pure and testable.
- Prefer RxJS composition over nested subscriptions.
- Unsubscribe when needed; use `async` pipe where possible.

## Testing
- Keep unit tests fast; isolate components.
- Use e2e tests for critical user journeys.
