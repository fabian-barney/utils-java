# AI Rules

This repository contains shared, versioned AI guidance intended to be vendored
into other projects (e.g., via git subtree). It is the single source of truth
for baseline agent rules across repositories.

## Getting Started
### Recommended AI agent

**Recommended: latest GPT Codex (xhigh)**  
*xhigh* reasoning is very important. If you do not have access to it, switch to
an alternative below.

**Alternative: latest Opus**  
Might work well in most cases, but expect it to be sloppy sometimes.

### Initial setup
1. Copy `AGENTS_TEMPLATE.md` into your project root as `AGENTS.md`.
2. Ask your AI agent to set up ai-rules in the desired mode.
   Example prompts:
   ```
   setup ai-rules
   setup ai-rules local
   setup ai-rules git
   setup ai-rules v4.3.0
   setup ai-rules local v4.3.0
   setup ai-rules git v4.3.0
   ```
   If you omit the version, the latest tagged release will be used.
   If you omit the mode, `local` is used by default.
   - `local` keeps ai-rules uncommitted on your machine.
   - `git` commits the subtree so the team can share it.

### Update
Ask your AI agent to update ai-rules when you want a newer version.
Example prompts:
```
update ai-rules
update ai-rules v4.3.0
```
If you omit the version, the latest tagged release will be used.
The update auto-detects the current mode (local or git) and preserves it.

### Switch modes
To switch modes after setup, ask your AI agent:
```
mode ai-rules local
mode ai-rules git
```
Switching to `git` will create commits to track the subtree. Switching to `local`
will remove the subtree from version control and keep it only on your machine.

## Structure
- `AI.md` - Single entry point for the entire ai-rules ruleset.
- `CORE/` - Non-negotiable baseline rules used across all projects.
- `AI-RULES/` - Maintenance guidance for this ruleset and release/update workflows.
- `PROGRAMMING/` - Programming task guidance.
- `PLAN/` - Planning guidance for execution tasks.
- `REVIEW/` - Code review standards and checklists.
- `SECURITY/` - Security practices and safeguards.
- `TEST/` - Testing strategy and expectations.
- `LANGUAGE/` - Language and coding conventions.
- `DESIGN/` - Design principles and clean code guidance.
- `ARCHITECTURE/` - Architecture patterns and system design rules.
- `FRAMEWORK/` - Framework-specific guidance.
- `BUILD_TOOLS/` - Build and dependency management tooling.
- `LIBRARY/` - Library-specific guidance.
- `COMPLIANCE/` - Compliance and licensing rules.
- `CI-CD/` - CI/CD and automation guidance.
- `INFRASTRUCTURE/` - Infrastructure and platform guidance.

## Contributing
- `CONTRIBUTING.md` - Contribution guidelines for this repository.

## Changelog
- `CHANGELOG.md` - Release history and notable changes.

## Usage (git subtree)
ai-rules can be vendored into another repository (for example with git subtree).
This keeps the rules in sync while still allowing you to pin a specific version.
You do not need to manage the mechanics manually if you use the AI prompts below.

## Versioning
Tag releases (e.g., `v1.0.0`) and pin subtree updates to a tag when stability
is required.
