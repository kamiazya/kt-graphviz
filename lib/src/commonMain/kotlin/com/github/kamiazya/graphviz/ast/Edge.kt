package com.github.kamiazya.graphviz.ast

/**
 * Represents an edge in a DOT graph's Abstract Syntax Tree (AST).
 *
 * The `Edge` class models relationships between graph elements by defining edge connections
 * through its target nodes and associated attributes. It combines multiple functionalities
 * from the `AST`, `GraphSTMT`, and `Parents` interfaces to allow integration into the greater
 * graph representation structure.
 *
 * @property targets A list of `EdgeDistribution` instances that specify the target nodes or
 * groups of nodes connected by the edge.
 * @property children A mutable list of child elements conforming to the `AttributesSTMT` type,
 * representing edge-specific attributes.
 */
data class Edge(
    var targets: List<EdgeDistribution>,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT>
