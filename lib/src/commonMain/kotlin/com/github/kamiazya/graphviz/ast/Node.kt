package com.github.kamiazya.graphviz.ast

/**
 * Represents a Node in the Abstract Syntax Tree (AST) of a DOT graph.
 *
 * A `Node` defines a specific component in the graph, identified by a unique `id`
 * and associated with a list of attribute-related statements (`children`).
 *
 * The `id` property uniquely identifies the node, represented by a `Literal` value,
 * which can specify its quoting style and string content. The `children` property
 * contains a mutable list of `AttributesSTMT`, which allows the node to define
 * specific attributes or other attribute-related groupings.
 *
 * This structure is typically used to model and manipulate nodes and their
 * associated attributes in a DOT graph representation, supporting tree-like
 * traversals and relationships within the broader AST.
 */
data class Node(
    var id: Literal,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT>
