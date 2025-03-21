package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.DotSTMT
import com.github.kamiazya.graphviz.ast.dsl.DotSTMTBuilder

/**
 * DotModel is an interface for dot models.
 */
public interface DotModel : Model<DotSTMT>, HasComment, HasRootGraph, HasContext {

    override fun toAST() = DotSTMTBuilder {
        comment?.let {
            comment(it)
        }
        root?.let {
            load(it.toAST())
        }
    }.stmts
}
