# VERSION_CONTROL_SYSTEM

Guidance for version control system usage (Git and others).

## Commit Messages
- Always include the ticket or issue identifier and ticket/issue title (or a
  concise equivalent summary aligned with that title).
- Example format: `<ticket-or-issue-id> <ticket-or-issue-title>: <change>`.
- Add optional context about what changed and why when it improves clarity.
- Mark breaking changes explicitly.

## Branch and PR/MR Workflow
- Create a dedicated branch for each new concern being implemented (usually one
  issue/ticket).
- Keep each branch scoped to that single concern.
- Push successful intermediate states to VCS so progress stays recoverable.
- Do not push knowingly non-working code unless this is explicitly requested.
- Suggest opening a PR/MR when all acceptance criteria are fulfilled and you
  have access to GitHub, GitLab, or a similar review system.
- When opening a PR/MR, auto-detect the target branch from downstream-project
  rules when available; otherwise ask which target branch to use and suggest
  the most likely one.

## PR/MR and Issue Tracker Summaries
- When creating a PR/MR, include an implementation summary aimed at code
  reviewers.
- If you have access to the review platform, add that summary directly to the
  PR/MR description or comment thread.
- Also provide a short bullet-point summary on the linked issue/ticket for
  Product Owners, Code Reviewers, and Testers/QA.
- If you have access to the issue tracker, add that summary directly to the
  linked issue/ticket.

### PR/MR Summary Template (Code Reviewer Audience)
```md
## Implementation Summary
- Scope:
- Key changes:
- Non-goals:

## Validation
- Tests executed:
- Manual checks:
- Residual risks:
```

### Issue Tracker Summary Template (PO/Reviewer/QA Audience)
```md
- Delivered:
- Acceptance criteria status:
- Validation status:
- QA notes / test focus:
- Open risks or follow-ups:
```

## Dependency Lock Files
- Commit dependency lock files for the package managers used by the project
  (for example `package-lock.json`, `pnpm-lock.yaml`, `yarn.lock`).
- Do not add ignore rules that exclude required lock files from VCS.
- In CI, install dependencies from lock files in frozen/immutable mode when
  supported by the package manager.
- Exceptions are allowed only when explicitly documented (for example,
  intentionally published libraries that choose not to commit lock files).

## Ignore File
- Maintain a VCS ignore file in the repository root (for Git: `.gitignore`).
- Keep the ignore list minimal but practical to prevent VCS pollution and protect sensitive files.
- Start from a baseline set, then add project- or tool-specific entries as needed.
- Remove or override entries if the repository intentionally versions those files.

### Minimal Must-Have Ignores
Secrets
- `.env`, `.env.*` (except `.env.example`)
- `.envrc`
- `*.key`, `*.pem`, `*.p12`, `*.pfx`
- `*.jks`, `*.keystore`

OS and editor noise
- `.DS_Store`, `Thumbs.db`, `Desktop.ini`
- `*.swp`, `*.swo`, `*~`
- `.idea/`, `.vscode/`

Build output, dependencies, caches, logs
- `target/`, `build/`, `dist/`, `out/`
- `node_modules/`
- `.gradle/`
- `__pycache__/`, `*.pyc`, `.pytest_cache/`
- `.npm/`, `.yarn/`, `.pnpm-store/`
- `coverage/`, `.nyc_output/`, `htmlcov/`
- `*.log`, `logs/`
- `tmp/`, `*.tmp`, `*.cache`
- `*.class`

## Language/Framework/Library/Build Tool Additions
- If a language/framework/library/build tool doc includes a "VCS Ignore Additions" section,
  add those patterns when using it.
- Keep additions scoped to generated output and local tooling noise.
- Do not ignore files that are meant to be versioned for reproducible builds.

## IDE/Tooling Additions
Apply only when the tool is used:
- Xcode: `DerivedData/`, `*.xcworkspace`, `*.xcuserdatad`, `*.xcuserdata/`, `*.xcuserstate`, `*.xccheckout`
- Visual Studio: `.vs/`, `*.suo`, `*.user`, `*.userosscache`, `*.sln.docstates`,
  `*.VC.db`, `*.VC.opendb`
- Android Studio: `*.iml`, `local.properties`, `captures/`, `.externalNativeBuild/`
  (plus `.idea/`, `.gradle/`, `build/` if not already)

## Safeguards
- Do not commit secrets; rotate and remove them from history if exposed.
- Review ignore rules when adding new tools to avoid accidental leaks.
