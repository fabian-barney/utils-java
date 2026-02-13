# LICENSES

Guidance for AI agents on software-license decisions in commercial
closed-source contexts.

This is engineering policy guidance, not legal advice.
Consult legal counsel for final legal decisions.

## Scope
- Define dependency-license acceptance and release-compliance workflow.
- Apply this file whenever adding/upgrading dependencies or distributing
  artifacts.

## Semantic Dependencies
- Inherit compliance baseline from `COMPLIANCE/COMPLIANCE.md`.
- This file is part of the cross-cutting baseline; downstream framework/library/
  build-tool docs must comply with this policy.

## Decision Framework
For each dependency (direct and significant transitive):
1. Identify authoritative license (SPDX + source repository/license file).
2. Classify risk (permissive / conditional / restricted).
3. Confirm obligations (notice, attribution, source disclosure, copyleft scope).
4. Verify compatibility with closed-source distribution model.
5. Record decision in dependency inventory/ADR when material.

## Generally Compatible (Permissive)
Typically acceptable for commercial closed-source use:
- Apache-2.0
- MIT
- BSD-2-Clause / BSD-3-Clause
- ISC
- Zlib
- BSL-1.0
- CC0-1.0
- Unlicense

## Conditional Licenses (Review Required)
Potentially acceptable with constraints and legal review:
- MPL-2.0
- LGPL-2.1-only / LGPL-2.1-or-later
- LGPL-3.0-only / LGPL-3.0-or-later
- EPL-2.0
- CDDL-1.0

## Generally Not Compatible for Closed-Source Distribution
Avoid unless legal explicitly approves alternative model:
- GPL-2.0-only / GPL-2.0-or-later
- GPL-3.0-only / GPL-3.0-or-later
- AGPL-3.0-only / AGPL-3.0-or-later
- SSPL-1.0
- Commons Clause / non-commercial-restricted licenses

## Attribution and Notice Workflow
- Preserve required copyright/license notices.
- For distributed artifacts, maintain `THIRD_PARTY_NOTICES.md` in the project
  root (or an explicitly documented equivalent path).
- Include NOTICE file obligations (for example Apache-2.0) in release outputs.
- Keep attribution metadata updated on dependency upgrades/removals.

## Transitive Dependency Governance
- Evaluate transitive licenses, not only direct dependencies.
- Flag unknown/custom licenses for manual review.
- Block merges/releases with unresolved license status.

## High-Risk Pitfalls
1. Approving dependency based only on package registry metadata.
2. Ignoring transitive copyleft obligations.
3. Missing NOTICE/attribution files in release artifacts.
4. License changes on dependency upgrade unnoticed.
5. Assuming "internal use" exception when software is distributed.

## Do / Don't Examples
### 1. License Verification
```text
Don't: trust single registry field blindly.
Do:    verify SPDX + upstream LICENSE file + release notes.
```

### 2. Upgrade Governance
```text
Don't: auto-merge major dependency upgrades without license recheck.
Do:    run license scan and review before merge/release.
```

### 3. Release Compliance
```text
Don't: ship binaries without third-party notices.
Do:    include required notices/licenses with release artifacts.
```

## Code Review Checklist for License Compliance
- Is license classification verified for new/updated dependencies?
- Are transitive licenses scanned and acceptable?
- Are attribution/notice obligations captured and implemented?
- Are restricted/conditional licenses escalated for legal review?
- Are license checks integrated into CI/release gating?

## Testing and Validation Guidance
- Run automated license scan in CI for every dependency change.
- Run full license + notice validation before release creation.
- Diff dependency tree on upgrades to detect new license obligations.
- Keep manual review log for exceptions and legal decisions.

## Override Notes
- Legal/compliance policy may be stricter than this file; stricter policy always
  wins.
