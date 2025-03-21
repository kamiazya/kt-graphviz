package com.github.kamiazya.graphviz.ast.dsl

/**
 * A marker annotation used to define DSL boundaries within AST-related builders.
 *
 * The `ASTDslMarker` is applied to DSL builders to prevent accidental access to
 * outer scopes when constructing structures in the Abstract Syntax Tree (AST).
 * This ensures clearer and more predictable DSL usage by enforcing context isolation.
 *
 * Classes annotated with `@ASTDslMarker` form a distinct DSL scope and are typically
 * used for constructing buildable components of DOT graph representations, such as
 * statements, nodes, edges, attributes, and subgraphs.
 */
@DslMarker
annotation class ASTDslMarker
