# DOCKER

Guidance for Docker usage.

## Defaults
- Keep images small and reproducible.
- Use multi-stage builds when possible.

## Best Practices
- Pin base image versions.
- Avoid running as root inside containers.
- Keep secrets out of images; use runtime injection.

## Quality
- Use health checks where supported.
- Clean up build artifacts and cache layers.
