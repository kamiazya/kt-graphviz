package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

public interface EdgeAttributeGroup {
    var color: String?
}

/**
 * EdgeAttributeGroupModel is an interface for edge attribute group models.
 */
public interface EdgeAttributeGroupModel : Model<GraphSTMT>, EdgeAttributeGroup, HasAttributes {
    override fun toAST() = GraphSTMTBuilder {
        attributeGroup(
            kind = AttributeGroup.Kind.EDGE,
        ) {
            for ((key, value) in attributes) {
                attribute(
                    key.unquated(),
                    value.toString().quated(),
                )
            }
        }
    }.stmts
}
