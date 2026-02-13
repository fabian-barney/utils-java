# HELM

Guidance for AI agents implementing and reviewing Helm charts.

## Scope
- Define Helm chart authoring, templating, and release management rules.
- Apply this file to chart templates, values files, and release workflows.

## Semantic Dependencies
- Inherit Kubernetes baseline from `INFRASTRUCTURE/KUBERNETES.md`.
- Inherit YAML safety from `LANGUAGE/YAML/YAML.md`.
- Inherit security constraints from `SECURITY/SECURITY.md`.

## Defaults
- Keep charts small, purpose-focused, and versioned.
- Keep values explicit and documented.
- Keep templates readable; avoid over-abstracted logic.
- Keep environment overrides controlled and minimal.

## Chart Structure and Values Governance
- Keep default `values.yaml` safe and production-aware.
- Group values by domain concern (image, resources, probes, ingress, etc.).
- Avoid hidden behavior behind implicit defaults.
- Prefer explicit booleans/enums over magic strings.
- Keep backward compatibility for chart consumers when evolving values.

## Templating Rules
- Prefer simple, readable template expressions.
- Avoid deep nested conditionals in templates.
- Use helper templates for repeated fragments.
- Validate required values explicitly (fail early with clear messages).
- Avoid business logic in chart templates.

## Release and Upgrade Strategy
- Use semantic versioning for charts.
- Document breaking value/schema changes clearly.
- Prefer additive changes and deprecation windows.
- Keep rollback strategy tested for critical services.

## Security and Secret Handling
- Do not commit plaintext secrets in chart values.
- Integrate secret management mechanisms (sealed/external secrets) as policy
  requires.
- Keep service account and security context defaults least-privilege.

## High-Risk Pitfalls
1. Template complexity making rendered output unpredictable.
2. Breaking values schema without migration path.
3. Environment values drift and copy-paste divergence.
4. Secrets committed in values files.
5. Implicit defaults that differ between environments unexpectedly.
6. Missing validation for required values.

## Do / Don't Examples
### 1. Values Safety
```text
Don't: hide required value assumptions deep in template logic.
Do:    validate required values with explicit failure message.
```

### 2. Secret Handling
```text
Don't: put production passwords in values.yaml.
Do:    reference platform-managed secret objects.
```

### 3. Template Simplicity
```text
Don't: encode complex branching/looping business logic in templates.
Do:    keep templates declarative and infrastructure-focused.
```

## Code Review Checklist for Helm
- Is chart structure clear and maintainable?
- Are values explicit, documented, and backward-compatible?
- Are templates readable with controlled complexity?
- Are required values validated and failure messages actionable?
- Are secrets handled via secure mechanisms?
- Is release/rollback strategy documented for breaking changes?

## Testing Guidance
- Run `helm lint` in CI.
- Run template render checks (`helm template`) with representative values.
- Validate rendered manifests against Kubernetes schema/policy checks.
- Test upgrade and rollback paths in staging for critical charts.

## Override Notes
- Platform-specific deployment policies may add stricter requirements, but chart
  readability, values governance, and secret safety here remain mandatory.
