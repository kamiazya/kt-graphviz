package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.Literal

/**
 * Provides utility methods and extensions for creating `Literal` instances.
 *
 * The `LiteralBuilder` interface defines functions to facilitate the creation of
 * `Literal` objects with different quoting styles. It includes methods for explicit
 * literal creation as well as extension functions to simplify the conversion of
 * strings into `Literal` instances with predefined quoting styles.
 */
interface LiteralBuilder {
    /**
     * Creates a `Literal` instance with the specified value and quoting style.
     *
     * This function constructs a `Literal` object using the provided `value` and `quoted` parameters.
     * The `Literal` represents a value in a DOT graph's AST, with quoting style behavior specified
     * by the `Literal.Quote` type.
     *
     * @param value The string value of the literal.
     * @param quoted The quoting style to apply to the literal. Defaults to `Literal.Quote.QUOTED`.
     */
    fun literalOf(
        value: String,
        quoted: Literal.Quote = Literal.Quote.QUOTED,
    ) = Literal(
        value = value,
        quoted = quoted,
    )

    /**
     * Converts the string to a `Literal` with a quoted style.
     *
     * This extension function creates a `Literal` object by wrapping the string
     * with a `Literal.Quote.QUOTED` style, indicating the string should be enclosed
     * in quotes within the DOT graph representation.
     *
     * @return A `Literal` instance with the value of the string and a `QUOTED` quoting style.
     */
    fun String.quoted(): Literal = literalOf(this, quoted = Literal.Quote.QUOTED)

    /**
     * Converts the string to a `Literal` with an unquoted style.
     *
     * This extension function creates a `Literal` object by wrapping the string
     * with a `Literal.Quote.UNQUOTED` style, indicating the string should not
     * be enclosed in quotes within the DOT graph representation.
     *
     * @return A `Literal` instance with the value of the string and an `UNQUOTED` quoting style.
     */
    fun String.unquoted(): Literal = literalOf(this, quoted = Literal.Quote.UNQUOTED)

    /**
     * Converts the string to a `Literal` with an HTML quoting style.
     *
     * This extension function creates a `Literal` object by wrapping the string with a
     * `Literal.Quote.HTML` style, indicating the string should be interpreted
     * and formatted as HTML content within the DOT graph representation.
     *
     * @return A `Literal` instance with the value of the string and an `HTML` quoting style.
     */
    fun String.html(): Literal = literalOf(this, quoted = Literal.Quote.HTML)
}
