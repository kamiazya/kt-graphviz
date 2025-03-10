package com.github.kamiazya.graphviz.model

/**
 * HasSubgraphs is an interface for models that have subgraphs.
 */
public interface HasSubgraphs {
    /**
     * A list of subgraphs.
     */
    var subgraphs: List<SubgraphModel>

    /**
     * Add a subgraph.
     */
    fun addSubgraph(subgraph: SubgraphModel) {
        subgraphs += subgraph
    }

    /**
     * Remove a subgraph.
     */
    fun removeSubgraph(subgraph: SubgraphModel) {
        subgraphs -= subgraph
    }

    /**
     * Get a subgraph by ID.
     *
     * @param id An ID of the subgraph.
     * @return A subgraph if found.
     *        null if not found.
     * @throws IllegalArgumentException if not found.
     */
    fun getSubgraph(id: String): SubgraphModel = getSubgraphOrNull(id) ?: error("Subgraph not found: $id")

    /**
     * Get a subgraph by ID or null.
     *
     * @param id An ID of the subgraph.
     * @return A subgraph if found.
     *       null if not found.
     */
    fun getSubgraphOrNull(id: String): SubgraphModel? = subgraphs.find { it.id == id }

    /**
     * Clear all subgraphs.
     */
    fun clearSubgraphs() {
        subgraphs = emptyList()
    }
}
