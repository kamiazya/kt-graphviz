package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * RootGraph is a class for root graph models.
 */
public abstract class RootGraph(
    context: ModelContext,
    override val directed: Boolean,
    override var strict: Boolean,
    id: String?,
    comment: String? = null,
) : RootGraphModel, BaseGraph(
    context = context,
    id = id,
    comment = comment,
)
