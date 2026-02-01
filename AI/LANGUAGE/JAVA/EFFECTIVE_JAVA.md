# EFFECTIVE_JAVA

This file is a concise, non-exhaustive ruleset inspired by Effective Java. It is
meant to guide reviews and implementations without quoting the book.

## Core Principles
- Prefer immutability; keep fields `final` where possible.
- Minimize mutability exposure; avoid returning internal mutable state.
- Favor static factories over constructors when they improve clarity.
- Use builders for constructors with many parameters.
- Prefer interfaces over concrete implementations in public APIs.
- Override `equals`, `hashCode`, and `toString` together when identity semantics
  matter.
- Use `Optional` for absent return values; avoid returning `null` from APIs.
- Fail fast with clear exceptions; validate arguments early.
- Make defensive copies of mutable inputs.
- Prefer composition over inheritance unless the subtype truly is-a supertype.

## Concurrency
- Avoid shared mutable state; use immutability or confinement.
- When sharing state, use proper synchronization and document invariants.

## Exceptions and Resources
- Use checked exceptions only for recoverable conditions.
- Prefer try-with-resources for closeable resources.

## Performance
- Avoid premature optimization; measure before tuning.
- Be mindful of allocation and hot paths in performance-critical code.
