package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AttributesSTMT
import com.github.kamiazya.graphviz.ast.dsl.AttributeSTMTBuilder
import com.github.kamiazya.graphviz.type.AttributeValue

/**
 * Represents an individual attribute within a graph model, consisting of a key and a value.
 *
 * The `Attribute` class implements the `Model` interface with a specific type parameter
 * `AttributesSTMT`, enabling transformation of the attribute into an abstract syntax tree (AST)
 * representation.
 *
 * Key-Value Usage:
 * - The `key` is a `String` representing the name of the attribute.
 * - The `value` is an `AttributeValue` representing the corresponding value of the attribute.
 *
 * Conversion to AST:
 * - Creates a corresponding attribute statement using the `AttributeSTMTBuilder`.
 * - The `key` is converted to an unquoted literal using the `unquoted` extension function.
 * - The `value` is converted to a quoted literal using the `quoted` extension function.
 */
data class Attribute(
    var key: String,
    var value: AttributeValue
) : Model<AttributesSTMT> {

    override fun toAST() = AttributeSTMTBuilder {
        attribute(
            key.unquoted(),
            value.toString().quoted(),
        )
    }.stmts
}
