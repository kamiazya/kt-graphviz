package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

/**
 * SubgraphModel is an interface for subgraph models.
 */
public interface SubgraphModel : Model<GraphSTMT>, BaseGraphModel {
    fun isCluster(): Boolean = id?.startsWith("cluster_") ?: false

    override fun toAST(): List<GraphSTMT> = GraphSTMTBuilder {
        comment?.let {
            comment(it)
        }
        subgraph(
            id = id?.quated(),
        ) {
            for ((key, value) in attributes) {
                attribute(
                    key.unquated(),
                    value.toString().quated(),
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
