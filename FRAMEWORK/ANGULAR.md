# ANGULAR

Guidance for AI agents implementing and reviewing Angular projects.

## Defaults
- Follow Angular style guide conventions.
- Prefer standalone APIs (`bootstrapApplication`, standalone components,
  function providers) for new code.
- Prefer `inject()` over constructor parameter injection in new code.
- Apply these rules by default in generated code and code reviews; deviate only
  with explicit, project-specific rationale.
- Before using version-sensitive or experimental APIs, verify the project's
  Angular major version and choose a supported fallback when needed.
- Keep components and directives focused on presentation concerns.
- Prefer signal-based local state and `computed` derivations.
- Treat signal `effect()` as a controlled escape hatch, not a default tool.
- Prefer explicit boundaries: component UI logic in components, shared logic in
  services/utilities.

## Structure
- Organize by feature area, not by technical type folders.
- Keep components small and focused; prefer one main concept per file.
- Keep related files together (`.ts`, template, styles, and `.spec.ts`).
- Match file names to the primary identifier and use hyphenated file names.

## Components and Templates
- Prefer `input()`, `output()`, and `model()` APIs for new components.
- Mark members initialized by Angular (`input`, `output`, queries) as
  `readonly` where applicable.
- Use `protected` for class members that are only read from the template.
- Name event handlers for the action performed (for example `saveUser()`),
  not by DOM event name (for example `handleClick()`).
- Keep templates declarative; move complex logic into TypeScript (often
  `computed`).
- Prefer `@if`, `@for`, and `@switch` blocks in modern Angular code.
- In every `@for`, use a stable `track` expression (`id`/`uuid`), not
  incidental identity.
- Use `$index` tracking only for truly static collections that never reorder or
  change length.
- Avoid identity tracking (`track item`) except as a last resort.
- Prefer native granular bindings (`[class.foo]`, `[style.width.px]`) over
  `ngClass` / `ngStyle`.
- Use `[class]` / `[style]` only when intentionally setting the full
  attribute.

## State, Signals, and RxJS
- Keep one clear source of truth; derive secondary state via `computed`.
- Avoid propagating state changes via `effect()`; use `computed` or explicit
  actions instead.
- Use RxJS composition operators (`switchMap`, `combineLatest`, `map`, etc.)
  instead of nested subscriptions.
- Prefer one reactive model per component/template:
  use `async` pipe for `Observable` view models, read signal view models
  directly, and convert at boundaries with `toSignal()` / `toObservable()`.
- Avoid mixing `Observable` and signal binding styles in the same template
  unless there is a strong reason.
- When using `toSignal()` / `toObservable()` outside an injection context
  (for example plain utility modules, static functions, or code created outside
  component/service construction), pass an explicit `Injector` option (for
  example `toSignal(source$, { injector })` or
  `toObservable(signalValue, { injector })`) so interop resources are torn down
  correctly.
- For `toSignal()`, `manualCleanup: true` keeps the subscription until the
  source completes and disables destroy-driven teardown.
  Use it only when the source is guaranteed to complete or when you explicitly
  manage lifecycle; otherwise it can leak.
- `toSignal()` surfaces Observable errors through signal reads:
  handle errors in the stream (for example with `catchError`) when you need a
  rendered error state instead of thrown reads.
  Prefer explicit renderable state (`{ status, value, error }`) or a sentinel
  fallback value for template paths.
- Signals created by `toSignal()` do not expose completion state.
  If completion matters (for example to render "done"), model it explicitly in
  stream/state (for example `{ status, value, error }`).
- Avoid manual subscriptions in components unless an imperative side effect is
  required.
- For imperative subscriptions, use `takeUntilDestroyed()` (or equivalent
  `DestroyRef` cleanup) to prevent leaks.
- Parameterless `takeUntilDestroyed()` works only in an injection context;
  otherwise pass `DestroyRef` explicitly.
  Example: `stream$.pipe(takeUntilDestroyed(this.destroyRef))` with
  `private readonly destroyRef = inject(DestroyRef)`.
- Be explicit about lifetime in services:
  root-provided services can keep subscriptions/effects alive until app
  teardown.
  These are effectively root effects, while effects tied to component injectors
  are view-scoped.

## `effect()` Policy
Effects synchronize Angular state with non-reactive or imperative systems.

### Allowed Uses
- Logging/analytics tied to signal changes.
- Synchronizing state to browser storage.
- Integrating with imperative APIs (canvas, charts, third-party widgets).
- Registering imperative behavior not expressible in template syntax.

### Discouraged Uses
- Deriving state from other state (use `computed`).
- Propagating state from one signal/store into another as workflow glue.
- Modeling user intent flow by "watching" state instead of handling the event.
- Coordinating request flows that are better expressed with RxJS pipelines.

### Guardrails
- Treat `effect()` as the last API choice:
  prefer `computed()` for derived values and `linkedSignal()` for
  derived-but-overridable values.
- Assume effects can re-run:
  always use `onCleanup` for effect-created resources (timers, listeners,
  subscriptions, observers, and similar handles).
- `effect()` creation requires an injection context:
  outside constructors/field initializers, pass an explicit `Injector`.
- Prefer `afterRenderEffect`/`afterNextRender` for DOM read/write that must
  happen after render.
- `afterRenderEffect` and `afterNextRender` callbacks run only on browser
  platforms, and components are not guaranteed to be hydrated before callbacks
  run; keep DOM access hydration-safe.
- Do not mutate SSR-produced DOM structure in post-render hooks unless
  hydration behavior is intentionally controlled.

## Dependency Injection and Services
- Keep services focused and composable; avoid "god services".
- Keep pure transformation logic framework-agnostic where possible.
- Scope providers intentionally (component/route/root) based on lifetime.
- Keep cross-cutting HTTP concerns in interceptors, not duplicated in
  components.
- Prefer functional interceptors for predictable behavior in complex setups.

## Routing and Data Loading
- Define route trees per feature and lazy-load feature boundaries with
  `loadComponent` / `loadChildren`.
- Use route guards/resolvers where navigation-level guarantees are required.
- Keep route-level dependencies near routes, using route provider scopes when
  helpful.
- Avoid unnecessary deep lazy-loading chains that create navigation waterfalls.

## HTTP and Error Handling
- Keep HTTP access in data services, not scattered across templates/components.
- For signal-first data loading, consider `httpResource` (experimental /
  version-dependent) only after verifying support in the current Angular major,
  and when you explicitly want a resource-style
  loading/error/value state model without ad-hoc subscriptions/interop.
- Model loading, success, and error states explicitly in UI-facing view models.
- Handle errors at the boundary where context exists (service/component), and
  map to actionable user-facing state.
- Do not swallow errors silently; either recover with an explicit fallback or
  report/log through the chosen observability path.
- Keep cross-cutting concerns (auth headers, retries, correlation IDs,
  standardized error mapping) in interceptors.

## Forms
- Prefer typed reactive forms for medium/large business forms.
- Prefer `NonNullableFormBuilder` or `{ nonNullable: true }` where null is not
  a valid domain state.
- Keep validators pure and reusable; place cross-field rules at group level.
- Avoid server-bound async validation on every keystroke:
  use `updateOn: 'blur'` / `updateOn: 'submit'` where appropriate.
- Remember `form.value` excludes disabled controls; use `getRawValue()` only
  when intentionally including disabled fields.

## Change Detection and Performance
- Prefer `ChangeDetectionStrategy.OnPush` for reusable or heavy component
  subtrees.
- Treat `OnPush` inputs as immutable boundaries; replace object references
  rather than mutating in place.
- Avoid expensive template calls and repeated allocations during change
  detection.
- Use stable track keys in list rendering to minimize DOM churn.
- Use `ChangeDetectorRef.markForCheck()` only when integrating with
  non-standard update paths.

## Zoneless Notes
- Base zoneless setup on the official Angular guidance for your exact major
  version: `https://angular.dev/guide/zoneless`.
- In projects configured for zoneless change detection, use the
  version-recommended setup (for example helper providers like
  `provideZonelessChangeDetection()` where applicable).
- In Angular versions where zoneless is the default (for example v21+), verify
  `provideZoneChangeDetection()` is not unintentionally re-enabling Zone.js
  semantics.
- Use `provideZoneChangeDetection()` only when intentionally opting into
  Zone.js semantics, and keep Zone.js runtime/test polyfills configured.
- For SSR with zoneless change detection, use `PendingTasks` /
  `pendingUntilEvent` to ensure required async render work finishes before
  serialization.
- Remove `NgZone.onStable` / `onMicrotaskEmpty` style "wait for stability"
  patterns; prefer `afterNextRender` / `afterEveryRender` or explicit DOM
  observers where appropriate.
- In zoneless apps, prefer clear Angular change notifications:
  signals read by templates, template/host listeners, `async` pipe, and
  `markForCheck()` at integration boundaries.
- When committing to zoneless mode and no longer relying on Zone.js semantics,
  remove `zone.js` and `zone.js/testing` from build/test polyfills.

## SSR and Hydration Notes
- Keep render paths SSR-safe: no unguarded `window` / `document` reads during
  render.
- Keep initial server and client markup consistent to avoid hydration mismatch
  and layout shift.
- Run browser-only integrations after render/hydration where possible.
- Some lifecycle hooks (for example initialization) run during SSR; do not
  assume "after render" implies "browser".
- Guard browser-only APIs with platform checks (for example
  `isPlatformBrowser` / `PLATFORM_ID`) and/or defer DOM work with SSR-safe
  primitives (for example `afterNextRender`).
- Evaluate third-party DOM-manipulating libraries for hydration compatibility.

## Security
- Never build Angular templates from user-controlled strings.
- Prefer template binding over direct DOM APIs.
- If direct DOM interaction is unavoidable, sanitize untrusted values with
  `DomSanitizer.sanitize` and the correct `SecurityContext`.
- Treat `bypassSecurityTrust*` as exceptional and document trust boundaries.
- Keep Angular updated and use production AOT builds.

## High-Risk Pitfalls
1. State propagation loops using `effect()` or late lifecycle hooks.
2. Nested subscriptions that leak and cause callback pyramids.
3. Missing teardown for subscriptions, timers, or observers.
4. `@for` without stable `track`, causing unnecessary DOM recreation.
5. In-place mutation of `OnPush` inputs, leading to stale UI.
6. Heavy template logic or methods that recompute on every check.
7. Writing to parent state during change detection (`NG0100` class errors).
8. Raw DOM writes (`innerHTML`, `ElementRef`) bypassing sanitization.
9. Component-level duplicated HTTP concerns instead of interceptor/service
   boundaries.
10. Assuming `HttpClient<T>` generics validate runtime payload shape.
11. Async error paths missing UI state updates, creating stuck spinners.
12. Root-scoped effects/subscriptions with no explicit lifetime strategy.

## Do / Don't Examples

### 1. Derived State with Signals
```ts
// Don't: propagate derived state via effect.
export class InvoiceBad {
  readonly subtotal = signal(100);
  readonly taxRate = signal(0.19);
  readonly total = signal(0);

  constructor() {
    effect(() => {
      this.total.set(this.subtotal() * (1 + this.taxRate()));
    });
  }
}

// Do: derive state with computed.
export class InvoiceGood {
  readonly subtotal = signal(100);
  readonly taxRate = signal(0.19);
  readonly total = computed(() => this.subtotal() * (1 + this.taxRate()));
}
```

### 2. Nested Subscriptions vs Composed Stream
```ts
// Don't: nest subscriptions in components.
export class UserPageBad {
  private readonly route = inject(ActivatedRoute);
  private readonly http = inject(HttpClient);

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const id = params.get("id");
      if (!id) return;
      this.http.get<User>(`/api/users/${id}`).subscribe((user) => {
        // assign user state
      });
    });
  }
}

// Do: compose with RxJS operators and expose a view-model stream.
export class UserPageGood {
  private readonly route = inject(ActivatedRoute);
  private readonly http = inject(HttpClient);

  readonly user$ = this.route.paramMap.pipe(
    map((params) => params.get("id")),
    filter((id): id is string => id !== null && id.trim().length > 0),
    distinctUntilChanged(),
    switchMap((id) => this.http.get<User>(`/api/users/${id}`)),
  );
}
```

### 3. Manual Subscription Cleanup
```ts
// Don't: subscribe imperatively without teardown.
export class NotificationsBad {
  private readonly bus = inject(NotificationBus);

  constructor() {
    this.bus.stream$.subscribe((message) => {
      console.log(message);
    });
  }
}

// Do: use takeUntilDestroyed for imperative subscriptions.
export class NotificationsGood {
  private readonly bus = inject(NotificationBus);

  constructor() {
    this.bus.stream$
      .pipe(takeUntilDestroyed())
      .subscribe((message) => console.log(message));
  }
}
```

### 4. Stable Tracking in `@for`
```html
<!-- Don't: omit track key; Angular cannot map rows efficiently. -->
@for (item of items()) {
  <app-user-row [user]="item"></app-user-row>
}

<!-- Do: use stable identity. -->
@for (item of items(); track item.id) {
  <app-user-row [user]="item"></app-user-row>
}
```

### 5. OnPush and Immutable Inputs
```ts
// Don't: mutate input object in place.
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProfileCardBad {
  readonly user = input.required<User>();

  uppercaseName(): void {
    this.user().name = this.user().name.toUpperCase();
  }
}

// Do: replace object references across OnPush boundaries.
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProfileCardGood {
  readonly user = input.required<User>();
  readonly userChange = output<User>();

  uppercaseName(): void {
    const current = this.user();
    this.userChange.emit({ ...current, name: current.name.toUpperCase() });
  }
}
```

## Code Review Checklist for Angular
- Is each component focused on presentation, with business logic extracted where
  sensible?
- Could this state derivation be `computed` instead of `effect()`?
- If `effect()` is used, is there a clear reason it cannot be
  `computed`/`linkedSignal`?
- Are RxJS/signal boundaries explicit (`toSignal()` / `toObservable()`) where
  crossing reactive models?
- Is one reactive model used per template, with a clear reason documented when
  mixing `Observable` and signal styles?
- If `toSignal()` / `toObservable()` are created outside an injection context
  (for example utility modules/static functions), is an explicit `Injector`
  (or equivalent cleanup strategy) provided?
- Are manual subscriptions avoided or cleaned with `takeUntilDestroyed`?
- If `takeUntilDestroyed()` is parameterless, is the usage site in an injection
  context?
- Are RxJS flows composed (no nested subscriptions)?
- Do all `@for` loops use stable `track` keys?
- Is `$index` used only for static lists and identity tracking avoided?
- Are `OnPush` boundaries respected with immutable updates?
- Are templates free of heavy logic and unnecessary method calls?
- Are cross-cutting HTTP concerns implemented via interceptors/services?
- Are loading/error states explicit, and are async failures surfaced safely?
- Is direct DOM access avoided or explicitly sanitized?
- For DOM post-render integrations, is `afterRenderEffect` used with hydration
  caveats considered?
- Are effects/subscriptions in root-provided services intentionally long-lived?
- Is zoneless configuration intentional for the Angular version in use?
- Is code safe for SSR/hydration (no unguarded browser globals in render)?
- Are forms typed, and are async validators performance-aware?
- Are route boundaries lazy-loaded where they improve startup without causing
  deep waterfalls?

## Testing Guidance for Angular-Specific Risks
- Follow general testing expectations in `TEST/TEST.md`.
- Add focused tests for `effect()` / signal interactions when side effects are
  intentional.
- Test list rendering updates for stable identity behavior in `@for` blocks.
- Test request cancellation/race behavior for route-driven data loading.
- Test manual subscription teardown on component destroy.
- For root-provided services, test or document lifetime expectations for
  long-lived subscriptions/effects.
- Test `OnPush` components with immutable input updates.
- Test typed form validators (sync and async), including invalid and edge-case
  states.
- If using zoneless change detection, ensure tests rely on Angular's scheduling
  signals instead of manual `detectChanges()` defaults.
- If SSR/hydration is relevant, test browser-global guards and hydration-safe
  render paths.
