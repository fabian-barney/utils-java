# AGENTS.md - AI Rules Setup Template

## Purpose
This file bootstraps the ai-rules baseline into a new project. Copy this file
into your project root as `AGENTS.md` and ask your AI agent to run the setup.

## Setup (run once)
Keep this template content in place until the final step so the instructions are
still available if the setup is interrupted.

1. Add the ai-rules subtree:
   git subtree add --prefix docs/ai https://github.com/fabian-barney/ai-rules.git main --squash
2. Baseline entry point (after subtree add):
   docs/ai/AI/AI.md
3. Create a local overlay for project-specific rules (recommended):
   AI_PROJECT.md
   Note: keep this outside `docs/ai/` so subtree updates do not overwrite it.
4. Create entry points for other AI tools:
   - `CLAUDE.md` (Claude Code)
   - `.github/copilot-instructions.md` (GitHub Copilot)
5. Replace this template content in `AGENTS.md` with the final references:
   - Baseline: docs/ai/AI/AI.md
   - Overlay: AI_PROJECT.md

## Entry Point Templates

### CLAUDE.md
```
# CLAUDE

Use the shared AI rules located at:
- Baseline: docs/ai/AI/AI.md
- Overlay: AI_PROJECT.md
```

### .github/copilot-instructions.md
```
# Copilot Instructions

Use the shared AI rules located at:
- Baseline: docs/ai/AI/AI.md
- Overlay: AI_PROJECT.md
```

> Note: The Copilot PR Review bot draws context from repository instructions.
> Keeping `.github/copilot-instructions.md` present and pointed at the baseline
> increases the chance it uses the same rules.

## Access
This repository is private; ensure your GitHub account has access.
