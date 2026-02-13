# CODE_REVIEW

Guidance for AI agents performing code and rules-document reviews.

## Scope
- Define review workflow, prioritization, and output expectations.
- Apply this file for PR/MR reviews and doc-rule audits.

## Semantic Dependencies
- Inherit review-layer boundary from `REVIEW/REVIEW.md`.
- Inherit all applicable technical constraints from relevant language/framework/
  library/build/infra docs.
- Inherit security/testing/compliance constraints and priority direction from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `COMPLIANCE/COMPLIANCE.md`.
- Severity levels are defined by this document's local severity model.

## Review Priority Order
1. Correctness and regression risk.
2. Security, privacy, and compliance.
3. Data integrity and error handling.
4. Architecture and boundary violations.
5. Performance and scalability risks.
6. Observability and operational readiness.
7. Maintainability/readability/test adequacy.

## Finding Severity Model
- `Critical`: exploitable security/data-loss/system outage risk.
- `High`: likely production failure or major correctness bug.
- `Medium`: maintainability/performance/reliability risk with non-trivial
  impact.
- `Low`: minor quality issue or consistency gap.

## Finding Format Requirements
Each finding should include:
- severity,
- impacted file/path reference,
- concrete issue description,
- why it matters (risk),
- actionable remediation guidance.

## Review Coverage Expectations
- Validate behavior changes against tests.
- Validate boundary adherence to semantic parent docs.
- Validate dependency additions for maturity/license/security fit.
- Validate error-handling and observability for failure paths.
- Validate migration/compatibility implications where relevant.

## Dependency Review Rules
- New dependencies require explicit justification.
- Reject niche/unmaintained dependencies unless strong rationale exists.
- Verify license compatibility with `COMPLIANCE/LICENSES.md`.
- Check overlap/redundancy with existing dependencies.

## Output Quality Rules
- Prioritize concrete findings over summaries.
- Avoid vague comments; anchor findings to code locations.
- Distinguish confirmed issues from assumptions/questions.
- State verification gaps explicitly (what was not tested/checked).

## High-Risk Pitfalls
1. Style-only feedback while missing correctness/security defects.
2. Vague findings without actionable remediation.
3. Missing boundary/dependency risk checks.
4. No distinction between critical risk and low-impact nits.
5. Ignoring test gaps for changed behavior.

## Do / Don't Examples
### 1. Finding Specificity
```text
Don't: "This looks wrong."
Do:    "High - `service/x.ts:87`: timeout is ignored; retries can hang request
        path under dependency outage. Add bounded timeout and failure mapping."
```

### 2. Priority Discipline
```text
Don't: spend review on naming nits while auth check is missing.
Do:    report missing auth as primary finding, then lower-priority nits.
```

### 3. Verification Transparency
```text
Don't: imply tests were run when they were not.
Do:    state which checks were run and what remains unverified.
```

## Review Checklist
- Are top-risk categories (correctness/security/compliance) covered first?
- Are findings severity-ranked and actionable?
- Are semantic parent-rule violations identified?
- Are dependency/tooling additions evaluated rigorously?
- Are test/verification gaps clearly documented?
- Is final output concise, concrete, and decision-useful?

## Override Notes
- Project-specific review policy may define extra gates, but severity-first,
  actionable, evidence-based review output here remains mandatory.
