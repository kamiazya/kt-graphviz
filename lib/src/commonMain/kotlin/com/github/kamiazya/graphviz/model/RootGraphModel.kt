package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.DotSTMT
import com.github.kamiazya.graphviz.ast.dsl.DotSTMTBuilder

/**
 * Defines the main model for representing a root-level graph in DOT syntax.
 *
 * The `RootGraphModel` interface combines the capabilities of a `Model` designed for
 * translating elements into DOT `DotSTMT` Abstract Syntax Tree (AST) statements,
 * and the extended functionality of a `BaseGraphModel` suitable for managing graph
 * properties, subgraphs, edges, and nodes.
 *
 * It supports features such as strictness and directedness settings for the graph,
 * ensuring compliance with DOT graph specifications.
 */
interface RootGraphModel : Model<DotSTMT>, BaseGraphModel {

    /**
     * Indicates whether the graph should be treated as a strict graph.
     *
     * The `strict` property determines if the graph prevents parallel edges
     * and multi-edges in its structure. When set to `true`, the graph adheres
     * to stricter constraints, ensuring that no two nodes may have multiple
     * edges connecting them in the same direction. This property aligns with
     * the DOT language's "strict" keyword and influences how the graph is
     * translated and rendered in DOT syntax.
     */
    var strict: Boolean

    /**
     * Represents whether the graph is directed or undirected.
     *
     * In the context of graph models, the `directed` property indicates
     * the type of relationships among the nodes:
     * - `true`: The graph is directed, meaning all edges have a specific direction.
     * - `false`: The graph is undirected, where the edges have no direction and
     *            represent bidirectional relationships between nodes.
     *
     * This property is typically used to determine the behavior of edge connections
     * and applicable algorithms for traversal, rendering, and analysis.
     */
    val directed: Boolean

    override fun toAST() = DotSTMTBuilder {
        comment?.let {
            comment(it)
        }
        rootGraph(
            id = id?.quoted(),
            strict = strict,
            directed = directed,
        ) {
            for ((key, value) in attributes) {
                attribute(
                    key.unquoted(),
                    value.toString().quoted(),
                )
            }
            load(graphAttributes.toAST())
            load(nodeAttributes.toAST())
            load(edgeAttributes.toAST())
            for (node in nodes) {
                load(node.toAST())
            }
            for (edge in edges) {
                load(edge.toAST())
            }
            for (subgraph in subgraphs) {
                load(subgraph.toAST())
            }
        }
    }.stmts
}
