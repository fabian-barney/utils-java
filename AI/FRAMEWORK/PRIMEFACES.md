# PRIMEFACES

Guidance for PrimeFaces projects.

## Defaults
- Follow PrimeFaces component conventions and lifecycle.
- Keep JSF views focused on presentation; move logic to backing beans/services.
- Prefer composite components for reuse.

## State and Data
- Avoid heavy view state; keep state minimal.
- Use proper scopes (request/view/session) intentionally.

## Performance
- Minimize large component trees.
- Avoid unnecessary Ajax updates.

## Testing
- Cover critical UI flows with integration or end-to-end tests.
