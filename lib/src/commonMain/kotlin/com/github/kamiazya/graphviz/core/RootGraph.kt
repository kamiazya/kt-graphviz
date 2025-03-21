package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * Represents a root graph in a graph model.
 *
 * The `RootGraph` class provides the primary structure for directed or undirected root graphs.
 * It also allows for optional strict mode, which enforces unique edges between nodes.
 * This class combines functionalities from `RootGraphModel` and `BaseGraph`, allowing it
 * to serve as the entry point for graph construction and manipulation.
 *
 * @constructor Initializes a new instance of the `RootGraph` class.
 * @param context The context for creating and managing the graph model.
 * @param directed A flag indicating whether the graph is directed.
 * @param strict A flag indicating whether strict mode is enabled, ensuring unique edges.
 * @param id An optional identifier for the graph.
 * @param comment An optional comment associated with the graph.
 *
 * Inherits attributes and behavior from `RootGraphModel` as well as `BaseGraph`,
 * which includes the support for nodes, edges, subgraphs, and attribute management
 * specific to graphs, nodes, and edges.
 */
abstract class RootGraph(
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
