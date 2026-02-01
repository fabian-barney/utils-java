# AI Rules (Baseline)

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
2. Ask your AI agent to set up ai-rules.
   Example prompt:
   ```
   setup ai-rules with version v2.1.1
   ```
   If you omit the version, the latest tagged release will be used.

### Update
Ask your AI agent to update ai-rules when you want a newer version.
Example prompt:
```
update ai-rules to version v2.1.1
```
If you omit the version, the latest tagged release will be used.

## Structure
- `AI/AI.md` - Single entry point for the entire ai-rules ruleset.
- `AI/CORE/` - Non-negotiable baseline rules used across all projects.
- `AI/AI-RULES/` - Maintenance guidance for this ruleset and release/update workflows.
- `AI/REVIEW/` - Code review standards and checklists.
- `AI/SECURITY/` - Security practices and safeguards.
- `AI/TEST/` - Testing strategy and expectations.
- `AI/LANGUAGE/` - Language and coding conventions.
- `AI/DESIGN/` - Design principles and clean code guidance.
- `AI/ARCHITECTURE/` - Architecture patterns and system design rules.
- `AI/FRAMEWORK/` - Framework-specific guidance.
- `AI/BUILD_TOOLS/` - Build and dependency management tooling.
- `AI/LIBRARY/` - Library-specific guidance.
- `AI/COMPLIANCE/` - Compliance and licensing rules.
- `AI/CI-CD/` - CI/CD and automation guidance.
- `AI/INFRASTRUCTURE/` - Infrastructure and platform guidance.

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
