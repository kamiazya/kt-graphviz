package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

/**
 * SubgraphModel is an interface for subgraph models.
 */
public interface SubgraphModel : Model<GraphSTMT>, BaseGraphModel {
    fun isCluster(): Boolean = id?.startsWith("cluster_") ?: false

    override fun toAST() = GraphSTMTBuilder {
        //
    }.stmts
}
