# Java 28 – Version Differences

## Goal
Practice key language, API, and runtime changes from Java 25 to 28 with focused examples.

## Features to Train (Java 25 to 28)
1. [preview] **Value Classes & Objects (Valhalla)** — [`ValueClasses.java`](src/java/edu/java28/ValueClasses.java): `value class`, identityless objects, heap flattening
2. [final] **Class-File API** (`java.lang.classfile`) — [`ClassFileApi.java`](src/java/edu/java28/ClassFileApi.java): standard bytecode parse/generation replacing ASM
3. [final] **Statements before `super(...)`** — [`StatementsBeforeSuper.java`](src/java/edu/java28/StatementsBeforeSuper.java): flexible constructor bodies, validate arguments before explicit constructor call
4. [final] **Module Import Declarations** — [`ModuleImportDeclarations.java`](src/java/edu/java28/ModuleImportDeclarations.java): `import module java.base;` for concise imports
5. [preview] **Primitive Types in Patterns** — [`PrimitiveTypesInPatterns.java`](src/java/edu/java28/PrimitiveTypesInPatterns.java): pattern matching for primitives in `instanceof` and `switch`
6. [preview] **Structured Concurrency** — [`StructuredConcurrency.java`](src/java/edu/java28/StructuredConcurrency.java): coordinate concurrent subtasks via `StructuredTaskScope`
7. [final] **Scoped Values** — [`ScopedValues.java`](src/java/edu/java28/ScopedValues.java): immutable thread-scoped data sharing replacing `ThreadLocal`
8. [final] **Stream Gatherers** — [`StreamGatherers.java`](src/java/edu/java28/StreamGatherers.java): standard custom intermediate operations via `Stream.gather()`
9. [preview] **Compact Object Headers (Lilliput)** — [`CompactObjectHeaders.java`](src/java/edu/java28/CompactObjectHeaders.java): reduced 64-bit object header size
10. [preview] **Code Reflection (Babylon)** — [`CodeReflection.java`](src/java/edu/java28/CodeReflection.java): inspect code models for GPU/heterogeneous execution
