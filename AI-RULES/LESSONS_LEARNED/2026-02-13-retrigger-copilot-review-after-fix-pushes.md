# 2026-02-13-retrigger-copilot-review-after-fix-pushes

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Issue
After fixing Copilot findings and pushing follow-up commits, the PR was merged
without a fresh Copilot review round on the latest commit.

## Prevention
- After every fix push, explicitly re-trigger Copilot review for that PR.
- Use the API-safe method instead of PR comment mentions:
  1. `gh pr view <PR_NUMBER> --json id --jq .id`
  2. `gh api graphql -f query="<MUTATION_QUERY>" -f pr="<PR_ID>" -f bots='copilot-pull-request-reviewer'`
     - Where `<MUTATION_QUERY>` is the complete
       `requestReviewsByLogin` GraphQL mutation and `<PR_ID>` is the value
       returned by step 1.
- Wait for the new review artifact and judge findings valid/invalid.
- Resolve/fix valid findings, reply on each thread, and repeat until clean.
