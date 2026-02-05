# LOMBOK

Lombok is strongly encouraged in Java projects. It reduces boilerplate and
improves readability when used consistently.

## Preferred Usage
- Use `@RequiredArgsConstructor` for dependency injection and for constructor
  injection with annotations like `@Value` or `@ConfigurationProperties`.
- Use `@Slf4j` for logging.
- Use `@Builder` for complex object construction.
- Use `@Value` for immutable DTOs.
- Use `@Getter` / `@Setter` when you need fine-grained control.

## Use With Care
- `@Data`: avoid on entities or types where equality is identity-based. Prefer
  explicit `@Getter` / `@Setter` and define `equals` / `hashCode` deliberately.
- `@SneakyThrows`: only with an adjacent line comment explaining why it is an
  appropriate use-case.
- `@EqualsAndHashCode(callSuper = true)`: only when superclass fields are part
  of identity.
- `@ToString`: avoid on types with heavy or recursive object graphs.

## Consistency
- Keep Lombok usage consistent across the codebase.
- Ensure annotation processing is enabled in the build and IDE.
- Add a `lombok.config` with `lombok.copyableAnnotations` configured. Scan the
  project for frameworks in use and include their relevant annotations so they
  are preserved on generated constructors (Spring annotations are common, but
  this is not limited to Spring).

Example `lombok.config`:
```
# This file tells lombok to copy certain annotations to generated constructors
# This is important for framework annotations like @Value, @Lazy, etc.

# Copy these annotations to generated constructors
lombok.copyableAnnotations += org.springframework.beans.factory.annotation.Value
lombok.copyableAnnotations += org.springframework.beans.factory.annotation.Qualifier
lombok.copyableAnnotations += org.springframework.context.annotation.Lazy
lombok.copyableAnnotations += org.springframework.beans.factory.annotation.Autowired
lombok.copyableAnnotations += org.jspecify.annotations.Nullable

# Additional Lombok configuration
lombok.addLombokGeneratedAnnotation = true
lombok.anyConstructor.addConstructorProperties = true
```
