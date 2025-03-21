package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.NodeModel

/**
 * Represents a node in a graph model.
 *
 * The `Node` class is used for defining graph nodes with custom attributes and metadata.
 * It inherits properties and behaviors from `NodeAttributeGroup` and implements the
 * `NodeModel` interface, enabling it to interact within the graph structure.
 *
 * This class allows the specification of attributes, such as shape and color, and provides
 * an optional comment to annotate the node within the graph representation.
 *
 * @property id The unique identifier for the node.
 * @property comment An optional comment associated with the node.
 */
class Node(
    override var id: String,
    override var comment: String? = null,
) : NodeAttributeGroup(), NodeModel
