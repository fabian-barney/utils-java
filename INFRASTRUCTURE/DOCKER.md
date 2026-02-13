# DOCKER

Guidance for AI agents implementing and reviewing Docker containerization.

## Scope
- Define secure, reproducible, and efficient container build/runtime rules.
- Apply this file to Dockerfiles and container runtime configuration.

## Semantic Dependencies
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit build reproducibility constraints from `BUILD_TOOLS/BUILD_TOOLS.md`.
- Inherit infrastructure constraints from `INFRASTRUCTURE/INFRASTRUCTURE.md`.

## Defaults
- Use multi-stage builds for production images.
- Pin base image versions/digests intentionally.
- Keep runtime images minimal and dependency-scoped.
- Run as non-root user by default.
- Keep image build deterministic and cache-friendly.

## Dockerfile Hygiene
- Order layers for cache efficiency (dependencies before changing sources).
- Minimize `COPY . .`; copy only required files per stage.
- Keep build-time and runtime dependencies separated.
- Use explicit `WORKDIR`, `USER`, and `ENTRYPOINT`/`CMD` semantics.
- Avoid shell form when exec form is clearer/safer.

## Security Baseline
- Do not bake secrets into images or layers.
- Use runtime secret injection and environment-specific provisioning.
- Remove package-manager caches and temporary build artifacts.
- Keep CVE scanning in CI for base and app layers.
- Drop unnecessary Linux capabilities at runtime where platform allows.

## Runtime and Operability
- Add health checks when service behavior allows meaningful probes.
- Keep graceful shutdown behavior compatible with container orchestration.
- Log to stdout/stderr for platform aggregation.
- Keep timezone/locale behavior explicit when domain-critical.

## Performance and Size
- Keep final image size controlled; remove unused tooling/binaries.
- Prefer distroless/slim bases when compatibility permits.
- Avoid unnecessary layer churn that invalidates cache frequently.

## High-Risk Pitfalls
1. Unpinned base image drift.
2. Running as root in production containers.
3. Secrets leaked via Dockerfile args/env/layers.
4. Shipping build tools in runtime image.
5. Opaque entrypoints swallowing signals.
6. Large context copies bloating image/cache churn.

## Do / Don't Examples
### 1. Multi-Stage Build
```text
Don't: build and run from same full SDK image.
Do:    compile in builder stage, copy artifact to slim runtime stage.
```

### 2. User Privileges
```text
Don't: default root user in runtime image.
Do:    create/use dedicated non-root user.
```

### 3. Secrets
```text
Don't: ARG API_KEY=... in Dockerfile.
Do:    inject secrets at runtime via platform secret store.
```

## Code Review Checklist for Docker
- Is base image pinned and maintained?
- Is build multi-stage and runtime image minimal?
- Is container running as non-root with least privilege?
- Are secrets excluded from image/layers?
- Are health/shutdown/logging behaviors orchestration-friendly?
- Is image size/cache behavior controlled?

## Testing Guidance
- Build image from clean cache in CI to verify determinism.
- Run vulnerability scan on produced images.
- Run container startup/healthcheck/shutdown integration tests.
- Validate runtime user/permissions and secret injection behavior.

## Override Notes
- Kubernetes/Helm/Istio docs may define runtime orchestration policies, but
  image hardening and reproducibility constraints here remain mandatory.
