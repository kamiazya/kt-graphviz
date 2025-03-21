package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.AST

/**
 * A generic abstract class designed to build and manage a list of statements within
 * the Abstract Syntax Tree (AST) structure.
 *
 * The `STMTBuilder` serves as a base class for constructing various types of statements
 * in a DOT graph representation. It allows for the accumulation and management of
 * statement-specific elements derived from the `AST` interface.
 *
 * @param T A type parameter constrained to subtypes of the `AST` interface.
 *          This ensures that only valid AST element types can be managed by the builder.
 */
abstract class STMTBuilder<T : AST> {
    /**
     * Represents a mutable list of statements managed within an Abstract Syntax Tree (AST) structure.
     *
     * This list is used to accumulate and store AST elements such as attributes, comments, nodes,
     * edges, subgraphs, or other structural components of a DOT graph.
     *
     * The type parameter `T` ensures that only elements conforming to the `AST` interface
     * can be added to the list, maintaining type safety and consistency within the AST structure.
     *
     * This property is commonly modified using helper functions in derived builders for appending
     * statements such as attributes, comments, or substructures.
     */
    var stmts: MutableList<T> = mutableListOf()

    /**
     * Adds all statements from the provided list to the existing statements managed by the builder.
     *
     * @param stmts A list of statements to be added. Each statement must conform to the type parameter `T`
     *              constrained by the `AST` interface.
     */
    fun load(stmts: List<T>) {
        this.stmts.addAll(stmts)
    }
}
