# JAXB

Guidance for JAXB usage.

## Defaults
- Use JAXB for XML serialization/deserialization when required by external
  contracts.
- Keep JAXB models separate from domain models when possible.

## Mapping
- Prefer explicit annotations over defaults for clarity.
- Keep schemas and bindings versioned alongside code.

## Validation
- Validate XML against schemas when external inputs are involved.
- Fail fast on invalid XML.

## Testing
- Use sample XML fixtures to verify bindings.
