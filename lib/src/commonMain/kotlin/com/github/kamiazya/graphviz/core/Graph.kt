package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.ModelContext

/**
 * Graph is a class for graph models.
 */
public class Graph(
    context: ModelContext,
    strict: Boolean = false,
    id: String?,
    comment: String? = null,
) : RootGraph(
    context = context,
    directed = false,
    strict = strict,
    id = id,
    comment = comment,
)
