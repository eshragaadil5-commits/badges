## Project Summary

Use_nullability_in_Kotlin focuses on Kotlin's type system and explicit null safety mechanisms from Google's Android Developer Training.

Functionality: Demonstrates how to safely handle variables that may contain null values without causing application crashes.

Key Concepts Learned:

Nullable vs. Non-Nullable Types: Differentiating standard types (String) from nullable counterparts (String?).

Safe-Call Operator (?.): Safely accessing properties or methods on nullable objects without risking exceptions.

Elvis Operator (?:): Supplying fallback default values whenever an expression resolves to null.

Smart Casting: Utilizing traditional if/else checks to automatically cast nullable variables into non-nullable scope.

Avoiding Assertions: Understanding why the not-null assertion operator (!!) should be avoided to prevent runtime NullPointerException crashes.

## Personal Reflection

This module provided a solid overview of Kotlin's "safety-first" philosophy, though the provided starter/practice code felt a bit unclear and disjointed at first.

Working through it required extra effort to understand how the examples connected, but breaking down the concepts step-by-step made the core lessons click. Practicing safe-call operators (?.) and the Elvis operator (?:) clearly demonstrated how Kotlin prevents runtime crashes, giving me a much better grasp on writing resilient code despite the confusing practice setup.
