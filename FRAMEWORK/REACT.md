# REACT

Guidance for React projects.

## Defaults
- Use functional components and hooks.
- Keep components small and focused.
- Prefer composition over inheritance.
- Treat `useEffect` as a controlled escape hatch, not a default tool.

## Naming Conventions
- This section only defines React-specific naming rules.
- React components: `PascalCase` (for example `UserProfileCard`).
- Custom hooks: `camelCase` with `use` prefix (for example `useUserProfile`).
- Non-React symbol naming follows general TypeScript conventions.

## State and Data
- Keep state minimal and local where possible.
- Avoid prop drilling by introducing context sparingly.
- Avoid unnecessary re-renders; memoize only when it matters.
- Derive values during render when possible.
- Prefer framework/server data-loading primitives over client `useEffect` fetching.

## Testing
- Follow general testing expectations in `TEST/TEST.md`.
- Use this file's effect-specific checklist and tests for `useEffect` behavior.

## `useEffect` Policy
Effects keep non-React systems in sync; keep React-only derivations in render.

Use `useEffect` when your component must connect, subscribe, schedule, observe,
or cancel external work.

### Allowed Uses
- Subscriptions: WebSocket, event emitters, browser event listeners.
- Timers and observers.
- Network requests that truly belong on the client.
- Imperative APIs or third-party widgets.

### Discouraged Uses
- Deriving render data from props/state.
- Triggering user intent by "watching state" instead of handling the event.
- Copying props to local state and trying to keep them in sync.
- Business logic that can run directly in reducers or handlers.

## Quick Decision Guide
- Need a value only for rendering:
  derive in render with plain expressions or `useMemo` if expensive.
- Need to react to user intent (click, submit, change):
  run side effects in the event handler.
- Need to subscribe to an external store:
  prefer `useSyncExternalStore`.
- Need setup/cleanup for an external system:
  use `useEffect`.

## SSR and Hydration Notes
- `useEffect` does not run during server rendering.
- If SSR/hydration is possible, avoid `window` / `document` reads during render.
- Guard browser-only logic in effects or safe initializers.

## High-Risk `useEffect` Pitfalls
1. Derived state effects:
   `setState(f(props))` in an effect adds extra renders and can loop.
2. Dependency ping-pong:
   unstable object/function dependencies retrigger effects continuously.
3. Stale closures:
   async callbacks and listeners read outdated values from old renders.
4. Missing cleanup:
   subscriptions, timers, and observers continue after unmount/dep changes.
5. Async race conditions:
   older requests resolve later and overwrite newer state.
6. Strict Mode surprises in development:
   non-idempotent setup causes duplicate side effects.
7. Effect chains:
   effect A sets state to trigger effect B, creating fragile temporal coupling.

## Safer Patterns and Alternatives
- Keep effects small and single-purpose.
- Co-locate setup and cleanup in the same effect.
- Keep effect callbacks synchronous:
  never mark the effect callback `async`; use an inner async function.
- Effect callbacks may return only cleanup or nothing.
- Keep dependency arrays honest; do not suppress `react-hooks/exhaustive-deps`
  without a documented reason.
- If adding a dependency causes a loop, redesign the flow instead of deleting
  the dependency.
- Use functional state updates to avoid stale closure bugs in intervals/callbacks.
- Use refs for mutable, non-render state that should not retrigger rendering.
- Extract repeated side-effect behavior into focused custom hooks.
- For subscription-style state (for example window size), prefer custom hooks
  based on `useSyncExternalStore`.
- Avoid effect chains:
  use one cohesive effect, or model flow with explicit actions/reducer/state machine.
- Handle non-abort async errors explicitly (state/reporting); do not `throw`
  from fire-and-forget async effect tasks.
  Throwing inside async effect tasks often becomes an unhandled rejection outside
  React error boundaries.
- If your React version supports `useEffectEvent`, use it for non-reactive
  callback reads instead of stale closure workarounds.
- Use `useLayoutEffect` only for DOM read/write that must run before paint.
- `useLayoutEffect` may warn in SSR; prefer `useEffect` unless pre-paint DOM
  reads/writes are required.

## Do / Don't Examples

### 1. Derived State
```tsx
// Don't: derive state from props via effect.
function PriceBad({ amount, taxRate }: { amount: number; taxRate: number }) {
  const [total, setTotal] = useState(amount);
  useEffect(() => setTotal(amount * (1 + taxRate)), [amount, taxRate]);
  return <span>{total.toFixed(2)}</span>;
}

// Do: derive during render.
function PriceGood({ amount, taxRate }: { amount: number; taxRate: number }) {
  const total = amount * (1 + taxRate);
  return <span>{total.toFixed(2)}</span>;
}
```

### 2. User Intent
```tsx
// Don't: watch state to trigger an action.
// Bad because user intent is now indirect state coupling and easy to mis-handle.
// Also breaks repeated clicks unless state is reset.
function SaveButtonBad({ onSave }: { onSave: () => Promise<void> }) {
  const [shouldSave, setShouldSave] = useState(false);
  useEffect(() => {
    if (shouldSave) void onSave();
  }, [shouldSave, onSave]);
  return <button onClick={() => setShouldSave(true)}>Save</button>;
}

// Do: perform user-driven side effects in the handler.
function SaveButtonGood({ onSave }: { onSave: () => Promise<void> }) {
  return <button onClick={() => void onSave()}>Save</button>;
}
```

### 3. Stale Interval Closure
```tsx
// Don't: interval callback captures stale "count".
function CounterBad() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    const id = setInterval(() => setCount(count + 1), 1000);
    return () => clearInterval(id);
  }, []);

  return <span>{count}</span>;
}

// Do: use functional updates for latest state.
function CounterGood() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    const id = setInterval(() => setCount((current) => current + 1), 1000);
    return () => clearInterval(id);
  }, []);

  return <span>{count}</span>;
}
```

### 4. Async Fetch Race and Cleanup
```tsx
// Don't: allow stale responses to overwrite newer state.
function UserProfileBad({ userId }: { userId: string }) {
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    async function load() {
      const response = await fetch(`/api/users/${userId}`);
      if (!response.ok) return;
      const data = (await response.json()) as User;
      setUser(data);
    }

    void load();
  }, [userId]);

  return user ? <ProfileCard user={user} /> : <Spinner />;
}

// Do: cancel stale requests and ignore abort errors.
function UserProfileGood({ userId }: { userId: string }) {
  const [user, setUser] = useState<User | null>(null);
  const [error, setError] = useState<Error | null>(null);

  useEffect(() => {
    const controller = new AbortController();
    // Prevent stale data from the previous request context.
    setError(null);
    setUser(null);

    async function load() {
      try {
        const response = await fetch(`/api/users/${userId}`, {
          signal: controller.signal,
        });
        if (!response.ok) {
          setError(new Error(`User fetch failed: ${response.status}`));
          return;
        }
        const data = (await response.json()) as User;
        setUser(data);
        setError(null);
      } catch (err) {
        if (err instanceof Error && err.name === "AbortError") {
          return;
        }
        setError(
          err instanceof Error ? err : new Error("Unknown user fetch error")
        );
      }
    }

    void load();
    return () => controller.abort();
  }, [userId]);

  if (error) return <span>Failed to load user.</span>;
  return user ? <ProfileCard user={user} /> : <Spinner />;
}
```

### 5. Subscription Cleanup
```tsx
// Don't: subscribe without cleanup.
// Bad because it reads `window` during render and leaks event listeners.
function WindowWidthBad() {
  const [width, setWidth] = useState(window.innerWidth);

  useEffect(() => {
    function onResize() {
      setWidth(window.innerWidth);
    }

    window.addEventListener("resize", onResize);
  }, []);

  return <span>{width}</span>;
}

// Do: always pair subscription setup with cleanup.
function WindowWidthGood() {
  const [width, setWidth] = useState(() =>
    typeof window === "undefined" ? 0 : window.innerWidth
  );

  useEffect(() => {
    if (typeof window === "undefined") return;

    function onResize() {
      setWidth(window.innerWidth);
    }

    // Initialize width immediately after mount.
    onResize();
    window.addEventListener("resize", onResize);
    return () => window.removeEventListener("resize", onResize);
  }, []);

  return <span>{width}</span>;
}
```

## Dependency Rules
- Never mark an effect callback `async`; create an inner async function.
- Effect callbacks return either cleanup or nothing.
- If an effect reads a reactive value, include it in dependencies.
- If adding a dependency breaks behavior, fix the design; do not hide the dep.
- Avoid inline object/function dependencies unless they are intentionally unstable.
- Stabilize dependencies only at true boundaries (`useMemo` / `useCallback`).
- Never disable `react-hooks/exhaustive-deps` globally.
- Enable `eslint-plugin-react-hooks` with `rules-of-hooks` and
  `exhaustive-deps`.

## Code Review Checklist for Effects
- Does this effect synchronize with an external system?
- Could this logic be derived during render instead?
- Could this logic run in an explicit user event handler instead?
- Is the effect callback synchronous and returning only cleanup or nothing?
- Are all reactive values read by the effect listed in dependencies?
- If dependencies are omitted, is there a documented, safe reason?
- Is setup idempotent under React Strict Mode development re-runs?
- Is cleanup present for every subscription, timer, observer, and listener?
- Does async work abort or ignore stale requests on dependency change?
- Can older async responses overwrite newer state?
- Is render code safe if SSR/hydration exists (no unguarded browser globals)?
- Is there effect chaining that should be replaced by explicit actions?
- Are inline dependencies causing unnecessary effect churn?
- Would extracting a custom hook reduce duplicated side-effect logic?
- Are lint suppressions for hooks justified and minimal?

## Testing Guidance for Effect-Heavy Code
- Test cleanup on unmount.
- Test cleanup on dependency change.
- Test race handling with fast/slow responses.
- Test that stale async results do not overwrite newer state.
- Test behavior under `StrictMode` for duplicate setup/cleanup safety.
- Test that user-intent side effects happen from handlers, not watcher effects.
- If SSR/hydration is relevant, test render paths that avoid browser globals.
