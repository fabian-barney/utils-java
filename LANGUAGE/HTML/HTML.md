# HTML

Guidance for AI agents implementing and reviewing HTML markup.

## Scope
- Define semantic, accessible, and secure HTML defaults.
- Apply this file for templates, server-rendered pages, component markup, and
  static documents.

## Semantic Dependencies
- Inherit naming/readability constraints from
  `LANGUAGE/CONVENTIONS.md` and `LANGUAGE/READABILITY.md`.
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit testing expectations from `TEST/TEST.md`.
- CSS/framework docs may specialize styling patterns, but should not weaken
  semantic and accessibility requirements.

## Defaults
- Use semantic HTML elements (`main`, `nav`, `section`, `article`, `button`).
- Prefer native controls over ARIA-heavy custom widgets when possible.
- Keep document structure valid and predictable:
  one `<main>` per page, logical heading hierarchy, meaningful landmarks.
- Keep markup declarative and free of presentation-only hacks.

## Accessibility Baseline
- Set the root document language (`<html lang="...">`) and set `dir` where
  bidirectional text requirements apply.
- Ensure interactive elements are keyboard reachable and operable.
- Every form control needs an accessible name (`label`, `aria-label`, etc.).
- Provide `alt` text for informative images; use empty alt for decorative
  images.
- Preserve heading order (`h1` -> `h2` -> `h3`) without skipping levels.
- Ensure sufficient text alternatives for icon-only controls.

## Forms and Inputs
- Use correct input types (`email`, `number`, `date`) to improve validation and
  assistive behavior.
- Associate labels explicitly with controls.
- Provide inline error feedback linked via accessibility attributes.
- Keep required/optional semantics explicit.

## Security and Injection Guardrails
- Never inject untrusted HTML directly into DOM output without sanitization.
- Avoid inline event handlers (`onclick`) in generated markup.
- Prefer escaping by default for dynamic text content.
- Treat URL-bearing attributes (`href`, `src`) as untrusted inputs and validate
  schemes.
- For links opened via `target="_blank"`, include `rel="noopener"`
  (typically `noopener noreferrer`) to prevent reverse-tabnabbing.

## Performance and Maintainability
- Avoid deeply nested DOM structures without semantic justification.
- Keep reusable UI structures componentized where framework allows.
- Prefer lazy-loading for non-critical media where applicable.
- Avoid duplicated IDs and non-unique `id` attributes.

## High-Risk Pitfalls
1. Generic `<div>` usage where semantic elements exist.
2. Click handlers on non-interactive elements without keyboard support.
3. Missing labels/alt text causing accessibility failures.
4. Duplicate IDs breaking selectors and accessibility references.
5. Unescaped dynamic HTML introducing XSS risk.
6. Incorrect heading structure harming navigation and SEO.

## Do / Don't Examples
### 1. Semantic Controls
```html
<!-- Don't: clickable div -->
<div onclick="submitOrder()">Submit</div>

<!-- Do: semantic button -->
<button type="button">Submit</button>
```

### 2. Image Accessibility
```html
<!-- Don't: missing alt -->
<img src="/avatar.png">

<!-- Do: informative alt text -->
<img src="/avatar.png" alt="Customer profile picture">
```

### 3. Label Association
```html
<!-- Don't: unlabeled input -->
<input id="email" type="email">

<!-- Do: explicit label -->
<label for="email">Email</label>
<input id="email" type="email" autocomplete="email">
```

## Code Review Checklist for HTML
- Are semantic elements used appropriately instead of generic wrappers?
- Is keyboard accessibility preserved for all interactions?
- Do controls have accessible names and clear labels?
- Is heading/landmark structure valid and navigable?
- Is dynamic content escaped/sanitized appropriately?
- Are duplicate IDs and invalid nesting avoided?
- Are form validation/error semantics accessible?

## Testing Guidance for HTML
- Add accessibility checks (automated and spot manual keyboard testing).
- Test form controls with screen-reader-friendly labels and errors.
- Test dynamic rendering paths for escaping/sanitization behavior.
- Validate document structure with HTML linting/validation tools.

## Override Notes
- Framework docs may define templating syntax, but semantic and accessibility
  obligations in this file remain mandatory.
