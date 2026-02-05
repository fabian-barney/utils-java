# GOF_DESIGN_PATTERNS

A concise, practical checklist inspired by the Gang of Four patterns. This is
not an exhaustive catalog; it focuses on when patterns are appropriate and how
not to overuse them.

## General Guidance
- Introduce patterns when you can articulate a likely change vector (new variants,
  integrations, policies, or behaviors) and the abstraction makes that change
  cheaper or safer.
- It is often harder to retrofit abstractions later; designing for extension
  early can be the pragmatic choice when the cost of change is high.
- Avoid pattern overuse and abstractions without a clear purpose.
- Name patterns explicitly in code or documentation when they clarify intent.

## Creational Patterns
- **Factory Method / Abstract Factory**: Use when construction logic varies by
  type and you want to isolate creation from usage.
- **Builder**: Use when an object has many optional parameters or complex
  construction steps.
- **Singleton**: Avoid in most cases; prefer dependency injection and explicit
  lifecycle management.

## Structural Patterns
- **Adapter**: Use to integrate with incompatible interfaces.
- **Facade**: Use to provide a simplified API over complex subsystems.
- **Decorator**: Use to add behavior without subclass explosion.
- **Proxy**: Use for lazy loading, access control, or remote calls.

## Behavioral Patterns
- **Strategy**: Use to swap algorithms at runtime without conditionals.
- **Observer**: Use for event-driven updates with loose coupling.
- **Command**: Use to encapsulate actions and support undo/redo or queuing.
- **State**: Use when behavior changes with internal state and conditionals
  become unmanageable.

## Anti-Patterns to Avoid
- Creating abstractions with no credible change vector or without a test seam.
- Hiding complexity behind patterns without reducing it.
- Using Singleton as a global variable.
