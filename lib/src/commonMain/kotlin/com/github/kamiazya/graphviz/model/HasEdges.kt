package com.github.kamiazya.graphviz.model

/**
 * HasEdges is an interface for models that have edges.
 */
public interface HasEdges {
    /**
     * A list of edges.
     */
    var edges: List<EdgeModel>

    /**
     * Add an edge.
     * @param edge An edge to add.
     */
    fun addEdge(edge: EdgeModel) {
        edges += edge
    }

    /**
     * Remove an edge.
     * @param edge An edge to remove.
     */
    fun removeEdge(edge: EdgeModel) {
        edges -= edge
    }

    /**
     * Clear all edges.
     */
    fun clearEdges() {
        edges = emptyList()
    }
}
