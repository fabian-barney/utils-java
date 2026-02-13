# PRIMEFACES

Guidance for AI agents implementing and reviewing PrimeFaces/JSF projects.

## Scope
- Define PrimeFaces-specific rules for JSF lifecycle, state handling, and UI
  behavior.
- Apply this file to JSF views, backing beans, and PrimeFaces component usage.

## Semantic Dependencies
- Inherit Java baseline from `LANGUAGE/JAVA/JAVA.md`.
- Inherit accessibility baseline from `LANGUAGE/HTML/HTML.md` and
  `LANGUAGE/CSS/CSS.md`.
- Inherit cross-cutting constraints from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.

## Defaults
- Keep JSF views presentation-focused.
- Keep business logic in services/use-cases, not backing beans.
- Use explicit bean scopes (`@RequestScoped`, `@ViewScoped`, `@SessionScoped`)
  based on lifecycle need.
- Prefer reusable composites/components for repeated UI patterns.

## State and Scope Rules
- Keep view/session state minimal.
- Avoid storing large mutable graphs in session scope.
- Keep conversation state explicit; clear state on flow completion.
- Do not use broader scope than needed.

## Component and Ajax Usage
- Keep partial updates targeted (`update`/`process` scopes explicit).
- Avoid broad page re-renders for small interactions.
- Keep component IDs stable and predictable.
- Prefer declarative component configuration over heavy imperative JS hacks.

## Validation and Error Handling
- Use Bean Validation for model constraints.
- Keep validation messages user-actionable.
- Handle conversion/validation failures consistently.
- Avoid swallowing backend exceptions in UI layer.

## Performance Baseline
- Minimize component tree complexity in heavy pages.
- Avoid unnecessary nested forms/components.
- Lazy-load large datasets where possible.
- Profile expensive render phases and optimize high-cost components.

## Security Baseline
- Enforce authorization on backend operations, not only UI rendering.
- Protect against CSRF/XSS with framework and platform controls.
- Avoid exposing sensitive data in hidden fields/view state.
- Keep file upload and input handling strictly validated.

## High-Risk Pitfalls
1. Business logic embedded in backing beans/view code.
2. Overly broad session scope causing memory bloat/stale state.
3. Unbounded ajax updates degrading performance.
4. Component tree complexity causing render latency.
5. UI-only authorization checks without backend enforcement.
6. Hidden state carrying sensitive information.

## Do / Don't Examples
### 1. Scope Selection
```text
Don't: store per-request form state in session scope.
Do:    use view/request scope according to interaction lifetime.
```

### 2. Ajax Update Targeting
```text
Don't: update="@all" for simple field interaction.
Do:    update only impacted region/component IDs.
```

### 3. Layering
```text
Don't: perform repository operations directly in backing bean action methods.
Do:    delegate to service/use-case layer.
```

## Code Review Checklist for PrimeFaces
- Are view/backing-bean responsibilities cleanly separated from business logic?
- Is bean scope minimal and intentional?
- Are Ajax process/update targets narrow and explicit?
- Are validation and conversion paths consistent and user-actionable?
- Is component tree complexity controlled?
- Are security checks enforced on backend boundaries?

## Testing Guidance
- Add integration/UI tests for critical user workflows.
- Test validation and conversion failure behavior.
- Test scope/lifecycle-sensitive behavior (view refresh/navigation/session).
- Test Ajax partial updates for correct UI state transitions.
- Add performance checks for heavy pages/components where needed.

## Override Notes
- Project-specific JSF conventions may add view-structure constraints, but scope
  minimization, layering, and security rules in this file remain mandatory.
