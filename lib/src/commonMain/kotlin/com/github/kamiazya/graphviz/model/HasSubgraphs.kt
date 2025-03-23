package com.github.kamiazya.graphviz.model

/**
 * Interface representing a graph or a model that has a collection of subgraphs.
 *
 * The `HasSubgraphs` interface provides functionalities to manage and
 * manipulate multiple subgraphs, including adding, removing, and querying
 * subgraphs by their identifiers. It is typically implemented by graph
 * models that consist of hierarchical or modular graph components.
 */
interface HasSubgraphs {
    /**
     * Represents a collection of subgraphs within a graph model.
     *
     * This property holds a list of `SubgraphModel` instances which define
     * individual modular or hierarchical components of a graph. Each subgraph
     * can be managed independently but exists as part of the larger graph structure.
     *
     * The `subgraphs` variable enables operations such as querying, adding, removing,
     * and managing subgraph components. It is a core part of the `HasSubgraphs` interface,
     * facilitating a structured and organized approach to complex, multi-component graphs.
     */
    var subgraphs: List<SubgraphModel>

    /**
     * Adds a subgraph to the collection of subgraphs in the graph model.
     *
     * @param subgraph The subgraph to be added, represented as an instance of `SubgraphModel`.
     *                 This allows the graph to include new modular or hierarchical components.
     */
    fun addSubgraph(subgraph: SubgraphModel) {
        subgraphs += subgraph
    }

    /**
     * Removes a subgraph from the collection of subgraphs in the graph model.
     *
     * @param subgraph The subgraph to be removed, represented as an instance of `SubgraphModel`.
     *                 This allows the graph to exclude specific modular or hierarchical components.
     */
    fun removeSubgraph(subgraph: SubgraphModel) {
        subgraphs -= subgraph
    }

    /**
     * Retrieves a subgraph with the specified ID.
     *
     * @param id The unique identifier of the subgraph to retrieve.
     * @return The subgraph matching the given ID.
     * @throws IllegalArgumentException if no subgraph with the specified ID is found.
     */
    fun getSubgraph(id: String): SubgraphModel = getSubgraphOrNull(id) ?: error("Subgraph not found: $id")

    /**
     * Retrieves a subgraph matching the specified ID from the collection of subgraphs.
     * If no subgraph with the given ID exists, returns null.
     *
     * @param id The unique identifier of the subgraph to retrieve.
     * @return The subgraph matching the given ID, or null if no such subgraph exists.
     */
    fun getSubgraphOrNull(id: String): SubgraphModel? = subgraphs.find { it.id == id }

    /**
     * Clears the collection of subgraphs in the graph model.
     *
     * This method resets the `subgraphs` property to an empty list,
     * effectively removing all existing subgraph entries. It is used when
     * the graph's subgraph structure needs to be reset or emptied.
     */
    fun clearSubgraphs() {
        subgraphs = emptyList()
    }
}
