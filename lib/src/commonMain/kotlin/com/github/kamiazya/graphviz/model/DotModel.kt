package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.DotSTMT
import com.github.kamiazya.graphviz.ast.dsl.DotSTMTBuilder

/**
 * DotModel represents a model specific to the DOT graph language format.
 *
 * This interface builds upon the core `Model` abstraction and includes
 * additional features that are specific to DOT graphs, such as support
 * for comments, root graphs, and contextual data.
 *
 * It combines the following capabilities:
 * - Comment handling through the `HasComment` interface.
 * - Root graph management through the `HasRootGraph` interface.
 * - Contextual awareness through the `HasContext` interface.
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
