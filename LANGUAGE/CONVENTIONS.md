# CONVENTIONS

Guidance for AI agents on cross-language naming and formatting conventions.

## Scope
- Define repository-wide conventions that apply before language/framework
  specializations.
- Apply this file when generating code and during code review across all
  languages.

## Semantic Dependencies
- Inherit global precedence and override rules from
  `CORE/RULE_DEPENDENCY_TREE.md`.
- Inherit readability constraints from `LANGUAGE/READABILITY.md`.
- Language-specific docs may narrow style details but must not silently weaken
  clarity or consistency constraints.

## Precedence Model
Formatting and naming decisions must follow this order:
1. Language/ecosystem standards and official style rules.
2. Repository conventions in this file.
3. Project-local conventions that do not conflict with 1 or 2.

Rules:
- Do not override established language standards with team preference.
- If multiple style tools exist, choose one canonical formatter/linter path.
- Resolve ambiguity by favoring consistency with existing code in the module.

## Formatting Baseline
- Use auto-formatters where available; do not hand-format against tooling.
- Keep multiline parameter/argument lists when item count is high (typically
  more than three) or readability improves.
- In multiline comma-separated lists, keep one item per line.
- Use trailing delimiters in multiline lists when language/tooling supports it.
- Keep import/group ordering deterministic.
- Avoid alignment formatting that is fragile under edits.

## Naming Baseline
- Use English names by default.
- Domain-native non-English terms are acceptable when they are canonical,
  precise, and consistently used.
- Prefer descriptive names over compressed/cryptic names.
- Names should express intent and role, not implementation mechanics.
- Keep naming consistent within module boundaries.

## Casing Rules (General)
Apply language-standard casing as default:
- Types/class-like constructs: `PascalCase`.
- Variables/functions/methods: follow language/ecosystem standard
  (`camelCase` in Java/JS/TS, `snake_case` in Python/Ruby, etc.).
- True constants: language-standard constant style
  (commonly `UPPER_SNAKE_CASE`, but follow language conventions).
- File/directory casing should follow ecosystem conventions and existing module
  pattern.

## Abbreviation Policy
- Avoid abbreviations unless broadly recognized.
- Initialism casing must follow the target language/ecosystem style guide.
- When the language style does not mandate all-caps initialisms, treat
  abbreviations as one word for casing (`userId`, `httpClient`, `xmlParser`).
- Do not mix styles for the same identifier family in one codebase.
- Prefer expanding obscure domain shorthand in public APIs.

## Identifier Quality Rules
- Boolean names should read as predicates (`isActive`, `hasAccess`).
- Collections should use plural names.
- Temporal values should include units (`timeoutMs`, `expiresAt`).
- Avoid misleading names that imply stronger guarantees than implementation.
- Avoid generic names (`data`, `result`, `temp`) unless scope is tiny and clear.

## Comment and Documentation Naming
- Keep comment terminology aligned with code identifiers.
- Rename related comments/docs when renaming key identifiers.
- Avoid stale terminology drift between code and documentation.

## High-Risk Pitfalls
1. Project-specific naming that contradicts language standards.
2. Inconsistent acronym casing across files/modules.
3. Generic names that hide domain meaning.
4. Unit-less numeric names causing interpretation errors.
5. Formatter drift from manual edits and inconsistent tool usage.
6. Naming collisions where different concepts share same identifier stem.

## Do / Don't Examples
### 1. Abbreviation Casing
```text
JS/TS/Java example:
Don't: getURL(), parseXML(), userID
Do:    getUrl(), parseXml(), userId

Language-style exception:
If a language/style guide mandates all-caps initialisms, follow that standard.
```

### 2. Unit Clarity
```text
Don't: retryTimeout = 5
Do:    retryTimeoutSeconds = 5
```

### 3. Generic Naming
```text
Don't: process(data)
Do:    processInvoiceBatch(invoiceBatch)
```

## Code Review Checklist for Conventions
- Are language-standard formatting and naming rules followed?
- Are naming decisions descriptive and consistent within module scope?
- Are abbreviations necessary and consistently cased?
- Are booleans/predicates named semantically?
- Are numeric/time fields unit-explicit?
- Are multiline formatting and trailing delimiter rules applied consistently?
- Did refactors keep identifier names aligned across code/comments/docs?

## Testing and Validation Guidance
- Keep formatter/linter checks mandatory in CI.
- Use naming-convention lint rules where ecosystem support exists.
- Add static checks for import ordering and formatting drift.
- Treat style lint failures as quality-gate failures, not optional warnings.

## Override Notes
- Language docs may define stricter naming rules (for example TypeScript enum
  member casing).
- Framework/library docs may define local naming idioms, but must remain
  compatible with this baseline and language standards.
