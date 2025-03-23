package com.github.kamiazya.graphviz.model

/**
 * Interface defining the capability of having and managing edges in a graph model.
 *
 * The `HasEdges` interface provides methods for maintaining a collection of edges
 * and offers operations such as adding, removing, and clearing edges. This interface
 * is typically used in graph-related models to manage edge representations and their
 * relationships with other graph components.
 *
 * Features:
 * - Supports a list of `EdgeModel` instances.
 * - Methods to modify the edge collection, providing flexibility for dynamic updates.
 */
interface HasEdges {
    /**
     * A list of edges representing the connections in a graph structure.
     *
     * Each edge is an instance of `EdgeModel`, which defines the attributes,
     * distributions, and other properties of an edge in the graph. This variable
     * allows accessing and managing the collection of edges within a class that
     * implements the `HasEdges` interface.
     *
     * Modifications to this list, such as adding or removing edges, can dynamically
     * update the structure of the graph.
     */
    var edges: List<EdgeModel>

    /**
     * Adds an edge to the current list of edges in the graph model.
     *
     * @param edge The edge to be added, represented as an instance of the `EdgeModel`.
     */
    fun addEdge(edge: EdgeModel): Unit {
        edges += edge
    }

    /**
     * Removes an edge from the list of edges in the graph model.
     *
     * @param edge The edge to be removed, represented as an instance of the `EdgeModel`.
     */
    fun removeEdge(edge: EdgeModel): Unit {
        edges -= edge
    }

    /**
     * Clears the list of edges in the graph model.
     *
     * This method removes all the edges from the `edges` collection,
     * resetting it to an empty list. It is typically used to clear
     * the graph's structure when all existing connections need to
     * be removed.
     */
    fun clearEdges(): Unit {
        edges = emptyList()
    }
}
