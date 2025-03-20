package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

/**
 * EdgeModel is an interface for edge models.
 */
public interface EdgeModel :
    Model<GraphSTMT>,
    HasComment,
    HasAttributes,
    HasEdgeDistributions,
    EdgeAttributeGroupModel {

    override fun toAST() = GraphSTMTBuilder {
        //
    }.stmts
}
