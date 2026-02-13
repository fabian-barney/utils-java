# YAML

Guidance for AI agents implementing and reviewing YAML configuration files.

## Scope
- Define YAML authoring rules for correctness, readability, and secure
  configuration management.
- Apply this file to CI/CD, infrastructure, and application config YAML files.

## Semantic Dependencies
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit naming/readability constraints from
  `LANGUAGE/CONVENTIONS.md` and `LANGUAGE/READABILITY.md`.
- Tool-specific docs (Kubernetes, Helm, GitLab) may specialize schema rules but
  must preserve these baseline safety constraints.

## Defaults
- Keep YAML files deterministic and explicit.
- Prefer spaces over tabs (tabs are invalid in YAML indentation).
- Keep indentation consistent (typically 2 spaces unless ecosystem demands 4).
- Keep keys stable and semantically named.
- Keep list item structure consistent across entries.

## Type and Parsing Safety
- Quote ambiguous scalars when type ambiguity is risky (`on`, `off`, `yes`,
  `no`, version-like numbers, leading-zero values).
- Prefer explicit booleans and numbers when schema expects them.
- Avoid relying on parser-specific coercion behavior.
- Keep date/time values explicit and consistently formatted.

## Anchors, Aliases, and Merge Keys
- Use anchors/aliases sparingly and only when they improve maintainability.
- Avoid deep merge trees that obscure effective configuration.
- Prefer explicit repetition over complex indirection when readability suffers.
- Document non-obvious anchor/merge usage in nearby comments.

## Secrets and Security
- Never commit plaintext secrets in YAML files.
- Use secret references/injection mechanisms provided by target platform.
- Keep environment-specific secret values outside source control.
- Validate secret key names and expected presence in deployment pipelines.

## Environment and Drift Management
- Keep base and environment overlays aligned with clear override intent.
- Avoid copy-paste divergence across environment files.
- Track schema version fields explicitly when supported.
- Keep default values safe; production overrides should be minimal and explicit.

## High-Risk Pitfalls
1. Indentation errors producing silent semantic changes.
2. Ambiguous scalar parsing (`on`, `off`, `yes`, `no`) causing type bugs.
3. Overuse of anchors/merges making effective config opaque.
4. Committing secrets or token-like values into version control.
5. Environment overlays drifting without validation.
6. Mixed list item shapes causing runtime parser/schema failures.

## Do / Don't Examples
### 1. Ambiguous Scalars
```yaml
# Don't: ambiguous values
featureFlag: on
releaseVersion: 01.02

# Do: explicit typing
featureFlag: true
releaseVersion: "01.02"
```

### 2. Secret Handling
```yaml
# Don't: commit plaintext secrets
database:
  password: super-secret
```

```yaml
# Do: reference an external secret source (valid Kubernetes example)
apiVersion: v1
kind: Pod
metadata:
  name: app
spec:
  containers:
    - name: app
      image: example/app:1.0.0
      env:
        - name: DATABASE_PASSWORD
          valueFrom:
            secretKeyRef:
              name: app-secrets
              key: database-password
```

### 3. Anchor Overuse
```yaml
# Don't: nested merges make effective config hard to reason about
appDefaults: &appDefaults
  image: app:1.2.3
  resources:
    requests: { cpu: "100m", memory: "128Mi" }
    limits: { cpu: "500m", memory: "512Mi" }
prod:
  <<: *appDefaults
  resources:
    <<: { requests: { cpu: "200m", memory: "256Mi" } }
```

```yaml
# Do: keep overrides explicit and shallow
prod:
  image: app:1.2.3
  resources:
    requests: { cpu: "200m", memory: "256Mi" }
    limits: { cpu: "500m", memory: "512Mi" }
```

## Code Review Checklist for YAML
- Is indentation/style consistent and parser-safe?
- Are ambiguous scalars quoted/typed explicitly?
- Are secrets excluded from committed YAML?
- Are anchors/aliases used sparingly and clearly?
- Are environment overrides intentional and minimal?
- Does file shape conform to expected schema/tool contract?

## Testing Guidance for YAML
- Validate YAML syntax in CI.
- Validate against target schemas (where available).
- Run dry-run/plan validation for deployment-related YAML.
- Test environment overlays to ensure expected effective config.
- Add checks preventing committed secrets.

## Override Notes
- Platform/tool docs may add schema-specific constraints.
- Baseline parsing safety and secret-handling rules in this file remain
  mandatory.
