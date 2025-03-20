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
        //
    }.stmts
}
