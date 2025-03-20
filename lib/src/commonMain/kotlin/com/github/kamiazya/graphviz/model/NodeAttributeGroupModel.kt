package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

public interface NodeAttributeGroup {
    var shape: String?
    var color: String?
}

/**
 * AttributeGroupModel is an interface for attribute group models.
 */
public interface NodeAttributeGroupModel : Model<GraphSTMT>, NodeAttributeGroup, HasAttributes {
    override fun toAST() = GraphSTMTBuilder {
        attributeGroup(
            kind = AttributeGroup.Kind.NODE,
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
