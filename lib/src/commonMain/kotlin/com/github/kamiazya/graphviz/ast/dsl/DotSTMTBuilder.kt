package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.Comment
import com.github.kamiazya.graphviz.ast.DotSTMT
import com.github.kamiazya.graphviz.ast.Literal
import com.github.kamiazya.graphviz.ast.RootGraph

/**
 * A builder class used for constructing DOT language statements in an Abstract Syntax Tree (AST) format.
 *
 * `DotSTMTBuilder` facilitates the creation and accumulation of various statements,
 * such as comments and root graphs, within the DOT graph representation. It serves as
 * a domain-specific language (DSL) entry-point for defining the structural elements
 * and relationships in a DOT graph.
 *
 * This builder is marked with the `@ASTDslMarker` annotation to ensure isolation of
 * its DSL context. It extends `STMTBuilder` with `DotSTMT` as the base type and
 * implements the `LiteralBuilder` interface to provide utility methods for working
 * with graph-related literals.
 *
 * @constructor Configures a new instance of `DotSTMTBuilder` with the provided initialization block.
 * In the block, DSL functions can be invoked to define statements.
 *
 * @param body An initialization block where DSL functions can be used to construct DOT statements.
 */
@ASTDslMarker
class DotSTMTBuilder(body: DotSTMTBuilder.() -> Unit) : STMTBuilder<DotSTMT>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Appends a new comment to the list of statements in the DOT AST builder.
     *
     * @param value The content of the comment to be added.
     * @param kind The type of the comment, specified as one of the predefined kinds in `Comment.Kind`.
     *             Defaults to `Comment.Kind.SLASH` if not explicitly specified.
     */
    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    /**
     * Appends a root graph to the current list of statements in the DOT AST builder.
     *
     * A root graph represents the entry point for defining a graph in the DOT language.
     * It can be configured as directed or undirected, and strict mode can be enabled
     * to enforce certain constraints according to the DOT specification. The graph
     * structure is defined in the provided initialization block.
     *
     * @param id An optional identifier for the root graph. Can be a literal value or null if no identifier is
     *           specified.
     * @param directed Indicates whether the graph is directed (`true`) or undirected (`false`).
     * @param strict Specifies whether the graph is strict (`true`) or not. Strict graphs prevent parallel edges
     *               between nodes.
     * @param init An initialization block where the graph's structure and properties are defined
     *             using a `GraphSTMTBuilder`.
     */
    fun rootGraph(
        id: Literal?,
        directed: Boolean,
        strict: Boolean,
        init: GraphSTMTBuilder.() -> Unit,
    ) {
        stmts += RootGraph(
            id = id,
            directed = directed,
            strict = strict,
            children = GraphSTMTBuilder(init).stmts
        )
    }
}
