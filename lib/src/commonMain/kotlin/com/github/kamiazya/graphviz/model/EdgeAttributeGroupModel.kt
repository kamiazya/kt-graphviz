package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

/**
 * Represents a model for defining and managing attribute groups specific to edges in a graph structure.
 *
 * `EdgeAttributeGroupModel` combines functionalities from multiple interfaces to work with edge attribute groups:
 * - `Model<GraphSTMT>`: Facilitates conversion of this model to a list of GraphSTMTs for constructing ASTs.
 * - `EdgeAttributeGroup`: Provides support for edge-specific attributes such as color.
 * - `HasAttributes`: Defines a way to handle generic attributes as key-value pairs.
 */
interface EdgeAttributeGroupModel : Model<GraphSTMT>, EdgeAttributeGroup, HasAttributes {
    override fun toAST() = GraphSTMTBuilder {
        attributeGroup(
            kind = AttributeGroup.Kind.EDGE,
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
