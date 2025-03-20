package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributesSTMT
import com.github.kamiazya.graphviz.ast.dsl.AttributeSTMTBuilder
import com.github.kamiazya.graphviz.type.AttributeValue

/**
 * Attribute is a pair of key and value.
 */
public data class Attribute(
    public var key: String,
    public var value: AttributeValue
) : Model<AttributesSTMT> {

    override fun toAST() = AttributeSTMTBuilder {
    }.stmts
}
