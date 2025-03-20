package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.ModelContext

/**
 * Digraph is a class for digraph models.
 */
public class Digraph(
    context: ModelContext,
    strict: Boolean = false,
    id: String?,
    comment: String? = null,
) : RootGraph(
    context = context,
    directed = true,
    strict = strict,
    id = id,
    comment = comment,
)
