package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

/**
 * Represents a group of attributes associated with a node in a graph structure.
 *
 * The `NodeAttributeGroup` interface allows customization and definition of various
 * attributes describing the appearance or properties of a node. It provides mutable
 * properties for specifying key visual attributes like `shape` and `color`.
 *
 * This interface is commonly used in graph-related models to encapsulate and manage
 * node-specific attribute configurations.
 */
interface NodeAttributeGroup {
    var shape: String?
    var color: String?
}

/**
 * Represents a model defining a group of node attributes within a graph structure.
 *
 * The `NodeAttributeGroupModel` interface combines functionality from multiple interfaces:
 * - `Model<GraphSTMT>`: Enables conversion of the attribute group into an Abstract Syntax Tree (AST) representation.
 * - `NodeAttributeGroup`: Provides capabilities to define and manage visual and structural node-specific attributes.
 * - `HasAttributes`: Manages a collection of key-value attribute pairs associated with the node.
 */
interface NodeAttributeGroupModel : Model<GraphSTMT>, NodeAttributeGroup, HasAttributes {
    override fun toAST() = GraphSTMTBuilder {
        attributeGroup(
            kind = AttributeGroup.Kind.NODE,
        ) {
            for ((key, value) in attributes) {
                attribute(
                    key.unquoted(),
                    value.toString().quoted(),
                )
            }
        }
    }.stmts
}
