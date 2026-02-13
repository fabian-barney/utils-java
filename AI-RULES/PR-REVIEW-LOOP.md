# PR-REVIEW-LOOP

Repository-standard PR review loop for ai-rules maintenance.

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Session Merge Preference (Ask Once)
- At the beginning of a session that uses this loop, ask exactly once:
  "After the loop reaches zero valid Copilot findings, should I also merge the
  PR?"
- Store the user's answer as session preference:
  `MERGE_AFTER_CLEAN_LOOP=true|false`.
- Do not ask this merge question again in the same session, even when handling
  multiple issues or PRs.
- After this one-time decision, run unattended through the loop unless blocked.

## Work Item Model
- Treat each issue/PR pair as one independent work item.
- Track per item:
  - `last_push_at`
  - `review_eligible_at = last_push_at + 5 minutes`
  - `has_open_review_threads`
  - `has_new_valid_findings`

## Loop (Round-Robin, Multi-Issue)
1. Build a queue of active work items.
2. For each item in round-robin order:
   - If code/docs updates are pending, push them.
   - After each push, set `review_eligible_at`.
   - If current time is before `review_eligible_at`, skip this item for now and
     continue with the next item (no idle waiting).
   - When eligible, collect Copilot review comments and review threads.
   - Classify each Copilot finding as valid or invalid.
   - Reply to each thread with the classification and concise rationale.
   - Fix valid findings, then resolve handled threads.
   - If any changes were pushed, re-trigger GitHub Copilot Code Review:
     - Get raw PR node ID:
       `gh pr view <PR_NUMBER> --json id --jq .id`
     - Request review from Copilot bot:
       `gh api graphql -f query="<MUTATION_QUERY>" -f pr="<PR_ID>" -f bots='copilot-pull-request-reviewer'`
     - Where `<MUTATION_QUERY>` is the complete
        `requestReviewsByLogin` GraphQL mutation and `<PR_ID>` is the value
        returned by the previous command.
     - Verify a new review request/review appears before judging latest state.
   - Move on to the next item after re-trigger.
3. Repeat until every active item has no new valid findings and no open review
   threads.

## Completion
- If `MERGE_AFTER_CLEAN_LOOP=true`, merge each PR when its loop is clean.
- If `MERGE_AFTER_CLEAN_LOOP=false`, stop at clean loop and report each PR as
  ready for user merge.

## Guardrails
- Keep PRs focused; do not bundle unrelated repository changes.
- Never delete review comments to make threads disappear.
- For invalid findings, leave a clear rationale before resolving.
- Never mention `@copilot` in PR comments.
