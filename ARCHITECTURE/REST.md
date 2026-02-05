# REST

Guidance for REST-style APIs.

## Principles
- Model APIs around resources and their relationships.
- Use HTTP methods consistently (GET, POST, PUT, PATCH, DELETE).
- Keep operations idempotent where appropriate (PUT, DELETE).

## Responses
- Use standard HTTP status codes.
- Return consistent error shapes.
- Prefer JSON with stable field names.

## Versioning
- Avoid breaking changes; use additive evolution where possible.
- If versioning is required, prefer URL or header-based versioning and document it.

## Performance
- Use pagination for collections.
- Support filtering and sorting for large datasets.

## Security
- Validate inputs and sanitize outputs.
- Use auth and authorization consistently at the resource boundary.
