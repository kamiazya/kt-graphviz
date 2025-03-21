package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

/**
 * Represents a subgraph model within a graph, capable of being converted into an Abstract Syntax Tree (AST)
 * representation.
 *
 * The `SubgraphModel` interface extends the functionalities of both `Model<GraphSTMT>` and `BaseGraphModel`, allowing
 * it to define and manage the structure and semantics of subgraphs. Subgraphs may include nodes, edges, attributes,
 * comments, additional nested subgraphs, and contextual metadata.
 */
public interface SubgraphModel : Model<GraphSTMT>, BaseGraphModel {
    /**
     * Determines whether the subgraph is identified as a cluster.
     *
     * A subgraph is considered a cluster if its ID starts with the prefix "cluster_".
     *
     * @return True if the subgraph ID starts with "cluster_", otherwise false.
     */
    fun isCluster(): Boolean = id?.startsWith("cluster_") ?: false

    override fun toAST(): List<GraphSTMT> = GraphSTMTBuilder {
        comment?.let {
            comment(it)
        }
        subgraph(
            id = id?.quoted(),
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
            for (sub in subgraphs) {
                load(sub.toAST())
            }
        }
    }.stmts
}
