# COGNITIVE_COMPLEXITY

Guidance for AI agents to control method-level cognitive complexity.

## Scope
- Define mandatory and target limits for method cognitive complexity.
- Apply during implementation, refactoring, and code review.
- Use this file with clean-code and SOLID guidance, not as an isolated metric game.

## Semantic Dependencies
- Inherit baseline precedence rules from `CORE/RULE_DEPENDENCY_TREE.md`.
- Inherit decomposition/readability guidance from `DESIGN/CLEAN_CODE.md`.
- Inherit responsibility and abstraction guidance from `DESIGN/SOLID.md`.

## Cognitive Complexity Targets
- Enforced limit for new methods: cognitive complexity `<= 15`.
- Encouraged limit for altered existing methods: cognitive complexity `<= 15`.
- Temporary tolerance for altered legacy methods: up to `20` is acceptable only
  when behavior risk is high and the PR includes a documented reduction plan.
- If an altered legacy method stays above `20`, create or link a follow-up
  issue with an explicit reduction plan.

Definition used in this document:
- Legacy method: a method that already existed before the current change and had
  cognitive complexity above the encouraged target (`> 15`) at change start.

These thresholds align with typical static-analysis defaults (for example SonarQube/SonarCloud)
and common IDE plugin conventions.

## Metric Determination and Evidence
Use the strongest available source in this order:
1. SonarQube/SonarCloud result for the branch/PR (preferred when available).
2. IDE/static-analysis plugin result (for example SonarLint/IntelliJ plugin).
3. Agent-side estimate when no tool result is available.

When using an estimate, state that it is an estimate and apply the same
thresholds conservatively.

## Complexity Reduction Heuristics
- Extract nested decision logic into named methods with single responsibility.
- Prefer early return/guard clauses to flatten nested condition pyramids.
- Separate orchestration from domain decision logic.
- Replace branch-heavy variant handling with Strategy/Polymorphism where
  variation is stable.
- Keep cross-cutting concerns out of core methods (use focused wrappers/aspects
  only when appropriate per `DESIGN/AOP.md`).
- Use `DESIGN/EARLY_RETURN.md` for guard-clause defaults, guardrails, and
  caveats.

## High-Risk Pitfalls
1. Treating metric thresholds as optional suggestions for new code.
2. Splitting methods mechanically without improving cohesion or naming.
3. Hiding complexity in boolean flags and multi-branch parameter behavior.
4. Ignoring legacy hot spots with complexity > 20 and no follow-up plan.
5. Claiming "tool unavailable" without providing a reasoned estimate.

## Do / Don't Examples
### 1. New Method Gate
```text
Don't: introduce a new method with cognitive complexity 24 and merge "as is".
Do:    refactor before merge until the method is <= 15.
```

### 2. Legacy Touch Rule
```text
Don't: modify a legacy method from 19 to 27 without mitigation.
Do:    reduce complexity during the change, or file a follow-up issue if > 20 remains.
```

### 3. Measurement Transparency
```text
Don't: state "complexity looks fine" without evidence.
Do:    attach Sonar/plugin metric or a documented estimate rationale.
```

## Code Review Checklist for Cognitive Complexity
- Do new methods stay at or below complexity 15?
- For altered existing methods, was complexity reduced or kept within target?
- If any altered method remains above 20, is there a linked follow-up issue?
- Is complexity evidence included (Sonar/plugin/estimate)?
- Were nested branches flattened with early returns where it improves clarity?
- Did refactoring improve readability/cohesion instead of only moving branches?

## Testing Guidance
- Add or update regression tests before major method decomposition.
- Validate behavior equivalence after complexity-reduction refactors.
- Add focused tests for extracted branches/decision paths.
- For deferred complexity reductions, capture risk and test focus in the
  follow-up issue/PR notes.

## Override Notes
- Language/framework docs may impose different local thresholds when justified,
  but they must not weaken the enforced new-method limit without explicit
  rationale and review.
