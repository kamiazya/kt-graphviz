package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

public interface GraphAttributeGroup {
    var color: String?
}

/**
 * GraphAttributeGroupModel is an interface for graph attribute group models.
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
