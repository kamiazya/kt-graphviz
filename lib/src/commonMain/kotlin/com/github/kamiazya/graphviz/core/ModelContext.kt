package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * A predefined instance of the `ModelContext` interface used for creating and managing
 * various graph model components, such as dots, digraphs, graphs, subgraphs, nodes, and edges.
 *
 * This instance provides a default implementation of the `ModelContext` interface methods, enabling
 * the creation of specialized graph models with specific configurations passed as parameters.
 *
 * Functions defined in this context:
 * - `createDot`: Creates a `Dot` instance with an optional comment, context, and root graph.
 * - `createDigraph`: Creates a `Digraph` (directed graph) instance with strict mode, an optional ID, and comment.
 * - `createGraph`: Creates a `Graph` (undirected graph) instance with strict mode, an optional ID, and comment.
 * - `createSubgraph`: Creates a `Subgraph` instance with an optional ID and comment.
 * - `createNode`: Creates a single `Node` instance with a required ID and an optional comment.
 * - `createEdge`: Creates an `Edge` instance connecting a list of edge targets, with an optional comment.
 *
 * This implementation allows for flexibility in defining graph elements within the context of
 * a pre-configured modeling environment.
 */
val MODEL_CONTEXT = object : ModelContext {
    override fun createDot(
        comment: String?,
        context: ModelContext,
        root: RootGraphModel?,
    ) = Dot(
        context = context,
        comment = comment,
        root = root,
    )

    override fun createDigraph(
        strict: Boolean,
        id: String?,
        comment: String?,
    ) = Digraph(context = this, strict = strict, id = id, comment = comment)

    override fun createGraph(
        strict: Boolean,
        id: String?,
        comment: String?,
    ) = Graph(context = this, strict = strict, id = id, comment = comment)

    override fun createSubgraph(id: String?, comment: String?) = Subgraph(
        context = this,
        id = id,
        comment = comment
    )

    override fun createNode(id: String, comment: String?) = Node(id = id, comment = comment)

    override fun createEdge(targets: List<EdgeDistribution>, comment: String?) = Edge(
        targets = targets,
        comment = comment
    )
}
