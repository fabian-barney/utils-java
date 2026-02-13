# CSS

Guidance for AI agents implementing and reviewing CSS.

## Scope
- Define CSS baseline rules for predictable, maintainable, and accessible UI
  styling.
- Apply this file for raw CSS, CSS modules, and framework-generated styles.

## Semantic Dependencies
- Inherit naming/readability guidance from
  `LANGUAGE/CONVENTIONS.md` and `LANGUAGE/READABILITY.md`.
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Framework styling docs (for example Tailwind) may specialize patterns but
  should not weaken accessibility or maintainability constraints.

## Defaults
- Prefer low-specificity, composable selectors.
- Use design tokens/variables for colors, spacing, typography, and sizing.
- Keep style concerns separate from markup structure where practical.
- Prefer modern layout systems (`flex`, `grid`) over float/position hacks.
- Keep responsive behavior mobile-first unless project constraints differ.

## Selector and Specificity Rules
- Avoid IDs in selectors for styling.
- Keep selector depth shallow.
- Avoid `!important` except for controlled utility/override cases with
  documented rationale.
- Prefer class-based selectors over element and descendant-heavy chains.
- Keep state styles explicit (`is-active`, `has-error` patterns).

## Architecture and Reuse
- Use consistent naming strategy (BEM/utility/component-based) per project.
- Co-locate component styles with component ownership boundaries.
- Avoid global leakage; scope styles where tooling supports it.
- Remove dead styles during refactors.

## Accessibility and UX Baseline
- Preserve visible focus indicators for keyboard navigation.
- Ensure color contrast meets accessibility requirements.
- Do not communicate state by color alone.
- Respect user preferences (`prefers-reduced-motion`, dark mode policy where
  applicable).

## Performance Baseline
- Avoid expensive selectors and overly broad wildcard patterns.
- Avoid unnecessary layout thrash via frequent class/style mutations.
- Keep animation properties to performant transforms/opacity when possible.
- Limit large paint-heavy effects on scrolling/high-frequency interactions.

## High-Risk Pitfalls
1. Specificity wars requiring escalating selectors or `!important`.
2. Hard-coded colors/sizes bypassing design token system.
3. Global selectors unintentionally overriding unrelated components.
4. Removing focus outlines without accessible replacement.
5. Deep nested selectors tightly coupled to DOM structure.
6. Animations causing motion/accessibility issues.

## Do / Don't Examples
### 1. Specificity
```css
/* Don't: deep chained selector */
.page .content .card .header .title { font-size: 1.2rem; }

/* Do: component class selector */
.card-title { font-size: 1.2rem; }
```

### 2. Token Usage
```css
/* Don't: hard-coded palette */
.button-primary { background: #3a62ff; color: #ffffff; }

/* Do: design token usage */
.button-primary {
  background: var(--color-primary);
  color: var(--color-on-primary);
}
```

### 3. Focus Handling
```css
/* Don't: remove focus indicator */
button:focus { outline: none; }

/* Do: provide accessible focus style */
button:focus-visible {
  outline: 2px solid var(--color-focus);
  outline-offset: 2px;
}
```

## Code Review Checklist for CSS
- Are selectors low-specificity and maintainable?
- Are design tokens used consistently?
- Are focus, contrast, and motion accessibility requirements preserved?
- Is responsive behavior intentional and testable?
- Are global side effects/leaks minimized?
- Is `!important` avoided or justified?
- Are performance-heavy patterns avoided on hot interaction paths?

## Testing Guidance for CSS
- Add visual regression tests for critical components/pages.
- Validate responsive behavior at key breakpoints.
- Run accessibility checks for contrast and focus visibility.
- Test interactive states (hover/focus/active/disabled/error).
- Verify style isolation to avoid unintended cross-component regressions.

## Override Notes
- Framework-specific styling systems (Tailwind, CSS-in-JS, component-scoped
  styles) may specialize implementation details but must keep these baseline
  accessibility and maintainability constraints.
