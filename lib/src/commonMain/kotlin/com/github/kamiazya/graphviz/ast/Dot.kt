package com.github.kamiazya.graphviz.ast

/**
 * Represents the root of a DOT Abstract Syntax Tree (AST).
 *
 * The `Dot` class is the primary container for a collection of `DotSTMT` elements,
 * which define the structure and content of a DOT graph.
 * It implements the `AST` interface, ensuring conformance to the AST hierarchy,
 * and the `Parents` interface, allowing it to maintain hierarchical relationships with its child elements.
 *
 * This class is typically used within DSLs or programmatic representations of DOT files.
 *
 */
data class Dot(
    override var children: MutableList<DotSTMT>,
) : AST, Parents<DotSTMT>
