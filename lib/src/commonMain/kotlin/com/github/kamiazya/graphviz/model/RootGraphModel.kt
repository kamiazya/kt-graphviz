package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.DotSTMT
import com.github.kamiazya.graphviz.ast.dsl.DotSTMTBuilder

/**
 * RootGraphModel is an interface for root graph models.
 */
public interface RootGraphModel : Model<DotSTMT>, BaseGraphModel {

    /**
     * A strict flag.
     */
    var strict: Boolean

    /**
     * A directed flag.
     */
    val directed: Boolean

    override fun toAST() = DotSTMTBuilder {
        comment?.let {
            comment(it)
        }
        rootGraph(
            id = id?.quated(),
            strict = strict,
            directed = directed,
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
            for (subgraph in subgraphs) {
                load(subgraph.toAST())
            }
        }
    }.stmts
}
