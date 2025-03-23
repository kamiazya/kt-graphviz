package com.github.kamiazya.graphviz.ast

/**
 * Represents a comment within a DOT graph's Abstract Syntax Tree (AST).
 *
 * The `Comment` class is used to include comments in a DOT graph representation.
 * A comment can be associated with various statement types such as attributes,
 * nodes, edges, or graph definitions. Comments can provide additional
 * explanations or metadata for the graph structure in a non-executable format.
 *
 * @param value The textual content of the comment.
 * @param kind The type of comment, specified by the `Kind` enum.
 */
data class Comment(
    var value: String,
    var kind: Kind,
) : AST, AttributesSTMT, DotSTMT, GraphSTMT {
    /**
     * Represents different kinds of comments that can exist in a DOT graph's Abstract Syntax Tree (AST).
     *
     * The `Kind` class defines the style or type of comment in the graph representation.
     * It is a sealed class to ensure that all possible types of comments are explicitly defined.
     */
    sealed class Kind {
        /**
         * Represents a block comment style in a DOT graph's Abstract Syntax Tree (AST).
         *
         * Block comments are typically used for multi-line annotations or descriptions
         * within the graph representation.
         */
        data object BLOCK : Kind()

        /**
         * Represents a single-line comment style in a DOT graph's Abstract Syntax Tree (AST).
         *
         * Single-line comments are prefixed by slashes and are typically used to add brief
         * annotations or remarks within the graph representation.
         */
        data object SLASH : Kind()

        /**
         * Represents a macro comment style in a DOT graph's Abstract Syntax Tree (AST).
         *
         * Macro comments may be used to represent preprocessing directives or macro-like behavior
         * within the graph representation.
         */
        data object MACRO : Kind()
    }
}
