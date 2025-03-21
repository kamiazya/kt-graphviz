package com.github.kamiazya.graphviz.ast

/**
 * Represents a literal value in a DOT graph's Abstract Syntax Tree (AST).
 *
 * A `Literal` is used to model values like graph identifiers, node or edge names,
 * attribute keys/values, and other concrete string elements in the DOT language.
 * It supports different types of quoting styles to allow flexible representation
 * of values, including:
 *
 * - `QUOTED`: Indicates the value is explicitly quoted.
 * - `UNQUOTED`: Indicates the value is not quoted.
 * - `HTML`: Indicates the value is treated as HTML content.
 *
 * @property value The string value of the literal.
 * @property quoted The type of quoting style applied to the literal.
 */
data class Literal(
    var value: String,
    var quoted: Quote = Quote.QUOTED,
) : AST {
    /**
     * Represents the quoting style of a literal value in the DOT graph's Abstract Syntax Tree (AST).
     *
     * The `Quote` sealed class defines the possible quoting styles that can be applied
     * to a literal value. This is used to determine how the value is formatted within
     * the graph representation.
     *
     * Types of quoting styles:
     * - `QUOTED`: Indicates the value is explicitly enclosed in quotes.
     * - `UNQUOTED`: Indicates the value is presented without quotes.
     * - `HTML`: Indicates the value is treated as HTML content, enabling HTML-specific formatting.
     */
    sealed class Quote {
        /**
         * Represents a quoting style where the value is explicitly enclosed in quotes.
         *
         * The `QUOTED` quoting style is a part of the `Quote` sealed class and is used to specify
         * that a literal value in the DOT graph's Abstract Syntax Tree (AST) should be formatted
         * with enclosing quote characters. This ensures proper representation of values that may
         * require quoting due to special characters or reserved keywords.
         */
        data object QUOTED : Quote()

        /**
         * Represents a quoting style where the value is presented without enclosing quotes.
         *
         * The `UNQUOTED` quoting style is part of the `Quote` sealed class and indicates
         * that a literal value in the DOT graph's Abstract Syntax Tree (AST) should be
         * formatted without enclosing quotation marks. This is typically used for values
         * that do not require quoting due to their simplicity or syntax compliance.
         */
        data object UNQUOTED : Quote()

        /**
         * Represents a quoting style where the value is treated as HTML content.
         *
         * The `HTML` quoting style is a part of the `Quote` sealed class and is used to
         * specify that a literal value in the DOT graph's Abstract Syntax Tree (AST)
         * should be interpreted and formatted as HTML content. This allows for
         * HTML-specific representations within a graph, such as HTML-like formatting in
         * node labels.
         */
        data object HTML : Quote()
    }
}
