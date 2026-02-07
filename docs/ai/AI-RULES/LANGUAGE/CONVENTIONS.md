# CONVENTIONS

## Formatting
Formatting rules are ordered by precedence:
1. Standard, language-specific formatting rules.
2. The ai-rules conventions regarding formatting.
3. Individual formatting preferences (lowest priority).

### Rules
- Follow standard language-specific formatting rules when they exist.
- It is not allowed to override standard formatting rules, even by team
  consensus.
- If not conflicting with standard formatting rules, apply the ai-rules
  conventions and keep them consistent across the codebase.
- For function/method parameter lists and function/method call argument lists
  with more than 3 items, use multiline formatting in new/changed code.
- If parameter/argument lists are multiline, place each item on its own line.
- If parameter/argument lists are multiline, require a trailing delimiter (for
  example, a trailing comma in comma-separated lists) on the last item when
  supported by the language.

## Naming Conventions
- Use English names by default.
- Non-English terms are allowed and encouraged when the project domain uses a
  specific term without a clear English translation or where the original term
  is more precise.
- Follow language-standard naming rules (e.g., class names, method names,
  constants).
- Abbreviations are forbidden unless they are widely known in general or in the
  project's domain.
- Treat an abbreviation as a single word for casing (e.g., `getUrl()` is
  correct; `getURL()` is incorrect).
- Prefer clear, descriptive names.
- Use consistent casing and prefixes within a codebase.
- Avoid misleading names; names must reflect intent.
