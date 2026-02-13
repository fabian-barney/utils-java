# TAILWIND_CSS

Guidance for AI agents implementing and reviewing Tailwind CSS code.

## Scope
- Define Tailwind-specific rules for scalable, readable, and accessible styling.
- Apply this file to Tailwind class usage, config, and component extraction.

## Semantic Dependencies
- Inherit CSS baseline from `LANGUAGE/CSS/CSS.md`.
- Inherit HTML accessibility baseline from `LANGUAGE/HTML/HTML.md`.
- Project/framework-specific docs may add component conventions, but should
  preserve this file's maintainability constraints.

## Defaults
- Prefer utility-first styling with clear class grouping by concern
  (layout, spacing, typography, color, state).
- Keep class lists readable and intentional.
- Use design tokens via Tailwind theme configuration instead of ad-hoc values.
- Prefer component extraction when class lists become repetitive or complex.

## Utility and Composition Rules
- Keep utilities local to component intent.
- Avoid giant one-off class strings with many conditionals.
- Prefer reusable abstractions for repeated UI patterns.
- Use `@apply` sparingly and only when it improves maintainability.
- Keep variant usage (`sm:`, `md:`, `hover:`, `focus:`) predictable and minimal.

## Design System Alignment
- Centralize colors, spacing, typography scales in config.
- Avoid arbitrary values unless genuinely required.
- Keep naming aligned with design language, not implementation details.
- Review custom plugins/utilities for long-term maintainability.

## Accessibility and UX
- Preserve focus-visible states.
- Ensure color contrast compliance.
- Ensure disabled/loading/error states are visually and semantically clear.
- Avoid motion-heavy transitions without reduced-motion consideration.

## Performance Baseline
- Ensure content paths are configured so unused classes are purged.
- Avoid dynamically constructed class names that evade static extraction.
- Keep generated CSS size bounded and monitored through the project's build and
  delivery standards.
- Prefer stable class composition over runtime string-generation complexity.

## High-Risk Pitfalls
1. Utility sprawl making components unreadable.
2. Arbitrary value overuse bypassing design system.
3. Dynamic class name generation breaking purge/content extraction.
4. Missing focus/contrast accessibility states.
5. Excessive responsive/state variants causing style bloat.
6. Duplicated style patterns across components with no abstraction.

## Do / Don't Examples
### 1. Reuse
```text
Don't: copy/paste same 20 utility classes in many files.
Do:    extract shared component or utility composition.
```

### 2. Arbitrary Values
```text
Don't: w-[317px] text-[#4a6dff] everywhere.
Do:    use theme tokens and approved spacing/size scales.
```

### 3. Dynamic Classes
```text
Don't: className={`text-${color}-600`}
Do:    map known variants to explicit class strings.
```

## Code Review Checklist for Tailwind
- Are class lists readable and grouped by concern?
- Are design tokens used instead of arbitrary values?
- Are repeated patterns extracted appropriately?
- Are dynamic classes purge-safe and explicit?
- Are accessibility states (focus/contrast/disabled/error) present?
- Is generated CSS size and variant usage controlled?

## Testing Guidance
- Add visual regression tests for shared components.
- Add accessibility checks for color contrast and focus states.
- Test responsive variants at key breakpoints.
- Validate production build output to ensure unused class purge works.

## Override Notes
- Project-specific design system docs may define stricter token/component
  constraints, but accessibility and maintainability baseline here remains
  mandatory.
