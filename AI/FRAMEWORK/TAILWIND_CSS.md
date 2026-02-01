# TAILWIND_CSS

Guidance for Tailwind CSS usage.

## Defaults
- Keep utility classes readable and grouped by purpose (layout, spacing,
  typography, color).
- Use design tokens consistently (colors, spacing, typography scales).
- Prefer component extraction when class lists grow large.

## Structure
- Create shared components for repeated UI patterns.
- Use `@apply` sparingly; prefer composition via components.

## Performance
- Remove unused classes via purge/content configuration.
- Avoid large custom utility layers unless necessary.

## Consistency
- Enforce class ordering via a formatter or lint rule.
- Keep responsive variants consistent across components.
