package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

public interface GraphAttributeGroup {
    var color: String?
}

/**
 * Represents a model for a group of graph attributes in a DOT graph structure.
 *
 * The `GraphAttributeGroupModel` interface extends the `Model`, `GraphAttributeGroup`,
 * and `HasAttributes` interfaces. It is primarily used to define and manage a collection
 * of graph-level attributes, typically within the context of building a DOT graph's
 * Abstract Syntax Tree (AST).
 *
 * Key responsibilities of this interface include:
 * - Managing graph-specific attributes.
 * - Providing an `attributes` property defined by the `HasAttributes` interface.
 * - Converting the attribute group into the AST representation of a DOT graph.
 */
public interface GraphAttributeGroupModel : Model<GraphSTMT>, GraphAttributeGroup, HasAttributes {
    override fun toAST() = GraphSTMTBuilder {
        attributeGroup(
            kind = AttributeGroup.Kind.GRAPH,
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
