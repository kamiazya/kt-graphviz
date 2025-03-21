package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.ModelContext

/**
 * Digraph represents a directed graph model.
 *
 * This class extends the `RootGraph` to specifically define a directed graph structure.
 * A directed graph, or digraph, consists of nodes connected by edges, where the edges have a direction.
 *
 * @property context The context used for creating and managing the directed graph.
 * @property strict A flag indicating whether the graph operates in strict mode.
 *                  In strict mode, duplicate edges and self-loops may be disallowed.
 * @property id The unique identifier for the directed graph, which can be nullable.
 * @property comment An optional comment associated with the directed graph.
 *
 * Inherits properties and functionality from the `RootGraph` class, including the ability to
 * manage nodes, edges, subgraphs, and attribute groups related to the graph structure.
 */
class Digraph(
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
