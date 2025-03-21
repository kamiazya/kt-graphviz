package com.github.kamiazya.graphviz.model.dsl

/**
 * A DSL marker for distinguishing scoping within the Graphviz model DSL.
 *
 * Applying this annotation helps to enforce the hierarchical structure and
 * scope of DOT graph creation by restricting the misuse of DSL receivers across scopes.
 *
 * This is used internally to achieve context-specific operations and maintain clarity
 * when building complex graph structures within a DSL block.
 */
@DslMarker
annotation class DotDslMarker