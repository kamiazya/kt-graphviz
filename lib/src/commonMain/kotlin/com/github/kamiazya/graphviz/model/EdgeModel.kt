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
        comment?.let {
            comment(it)
        }
        edge({
            for (target in targets) {
                load(target.toAST())
            }
        }) {
            for (attr in attributes) {
                load(attr.toAST())
            }
        }
    }.stmts
}
