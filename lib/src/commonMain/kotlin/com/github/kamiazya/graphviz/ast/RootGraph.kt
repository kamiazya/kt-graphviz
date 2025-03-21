package com.github.kamiazya.graphviz.ast

/**
 * Represents the root graph in a DOT graph's Abstract Syntax Tree (AST).
 *
 * RootGraph is a data class that models the main graph structure in a DOT representation.
 * It encapsulates properties such as the graph's identifier, its directed/undirected nature,
 * strict mode, and a list of child statements that define the graph's structure and attributes.
 *
 * @property id The literal identifier of the graph. It can be null to represent an anonymous graph.
 * @property directed A boolean indicating whether the graph is directed (true) or undirected (false).
 * @property strict A boolean indicating whether the graph enforces a strict structure, which disallows
 * duplicate edges or parallel edges in the graph structure.
 * @property children A mutable list of GraphSTMT elements representing the child statements within the graph.
 * These may include nodes, edges, attributes, subgraphs, or other graph statements.
 */
data class RootGraph(
    var id: Literal? = null,
    var directed: Boolean,
    var strict: Boolean,
    override var children: MutableList<GraphSTMT>,
) : AST, DotSTMT, Parents<GraphSTMT>
