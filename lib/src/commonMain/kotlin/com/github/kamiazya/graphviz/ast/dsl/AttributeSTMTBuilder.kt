package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.Attribute
import com.github.kamiazya.graphviz.ast.AttributesSTMT
import com.github.kamiazya.graphviz.ast.Comment
import com.github.kamiazya.graphviz.ast.Literal

/**
 * A builder class for creating and managing attribute-related statements in a DOT graph's AST.
 *
 * The `AttributeSTMTBuilder` is a DSL-based utility that facilitates the construction of statements
 * categorized as `AttributesSTMT`. These statements include attributes and comments specific to graph
 * elements such as nodes, edges, or the overall graph itself. The builder utilizes the `STMTBuilder`
 * as its base class and implements the `LiteralBuilder` interface for handling literals.
 *
 * This class is marked with `@ASTDslMarker` to define a distinct scope in the DSL and prevent accidental
 * access to outer scopes.
 *
 * @constructor Initializes the builder with a lambda block defining its structure.
 * @param body A lambda function with the receiver type `AttributeSTMTBuilder` used to define and populate
 *             the builder's structure.
 */
@ASTDslMarker
class AttributeSTMTBuilder(body: AttributeSTMTBuilder.() -> Unit) : STMTBuilder<AttributesSTMT>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Appends a comment to the list of statements managed by the builder.
     *
     * @param value The textual content of the comment to be added.
     * @param kind The type of comment to use, specified by the `Comment.Kind` enum. Defaults to `Comment.Kind.SLASH`.
     */
    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    /**
     * Adds an attribute to the list of statements in the builder.
     *
     * @param key The key of the attribute being created, represented as a `Literal`.
     * @param value The value of the attribute being created, represented as a `Literal`.
     */
    fun attribute(
        key: Literal,
        value: Literal,
    ) {
        stmts += Attribute(
            key = key,
            value = value,
        )
    }
}
