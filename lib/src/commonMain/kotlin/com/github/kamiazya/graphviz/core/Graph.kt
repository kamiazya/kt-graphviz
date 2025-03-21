package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.ModelContext

/**
 * Represents an undirected graph model.
 *
 * The `Graph` class is used to define an undirected graph structure. It extends the `RootGraph`
 * class, inheriting its functionalities and properties, including support for nodes, edges,
 * subgraphs, and graph attributes.
 *
 * This class specifically models a graph where edges do not have a direction. It allows for
 * the creation and manipulation of graph elements using the provided `ModelContext`.
 *
 * @property context The context used for creating and managing the undirected graph.
 * @property strict A flag indicating whether the graph operates in strict mode. In strict mode,
 *                  duplicate edges and self-loops may be disallowed. Default is false.
 * @property id The unique identifier for the graph, which can be nullable.
 * @property comment An optional comment associated with the graph.
 */
class Graph(
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
