package com.github.kamiazya.graphviz.ast

/**
 * Represents a reference to a graph node in the DOT language's Abstract Syntax Tree (AST).
 *
 * This class is primarily used to model node references for edges in a graph,
 * including optional port information and compass points.
 *
 * NodeRef combines the ability to reference specific parts of a graph structure,
 * such as nodes and their sub-elements, with flexibility for additional configuration.
 *
 * @property id The identifier of the node being referenced.
 * @property port Optional port information for the node, which specifies
 * subcomponents within the node.
 * @property compass Optional compass point for the node, used to indicate the
 * direction relative to the node's geometry.
 */
data class NodeRef(
    var id: Literal,
    var port: Literal? = null,
    var compass: Literal? = null,
) : AST, EdgeDistribution
