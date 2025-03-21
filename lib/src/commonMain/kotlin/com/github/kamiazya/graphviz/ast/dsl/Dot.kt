package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.Dot

/**
 * Builds a `Dot` instance using the specified initialization block.
 *
 * This method provides a DSL (Domain Specific Language) entry-point for constructing the
 * root of a DOT Abstract Syntax Tree (AST) through the `DotSTMTBuilder`. The initialization block
 * allows defining various DOT statements such as comments, graphs, nodes, and edges.
 *
 * @param init An initialization block used to configure the DOT graph statements. This block
 *             operates on a `DotSTMTBuilder` context, providing DSL functions to define
 *             the structure and properties of the graph.
 * @return A `Dot` instance representing the root AST element that encapsulates the constructed
 *         DOT statements.
 */
fun dot(
    init: DotSTMTBuilder.() -> Unit,
): Dot = Dot(
    DotSTMTBuilder(init).stmts
)
