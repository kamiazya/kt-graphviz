package com.github.kamiazya.graphviz.ast

/**
 * Represents a subgraph within a DOT graph's Abstract Syntax Tree (AST).
 *
 * A `Subgraph` is a structural component used to define a nested graph
 * or a grouping of nodes and edges within the larger graph structure.
 * It is commonly used to apply attributes or organize nodes and edges
 * in a hierarchical or modular way.
 *
 * @property id An optional identifier for the subgraph, defined as a `Literal`.
 * If present, this identifier is used to uniquely name or reference the subgraph
 * within the scope of the graph.
 *
 * @property children A mutable list of graph statements (`GraphSTMT`) that
 * represent the content of the subgraph. This includes nodes, edges,
 * attributes, comments, or additional nested subgraphs.
 */
data class Subgraph(
    var id: Literal? = null,
    override var children: MutableList<GraphSTMT>,
) : AST, GraphSTMT, Parents<GraphSTMT>
