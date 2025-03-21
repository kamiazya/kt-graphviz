package com.github.kamiazya.graphviz.model

/**
 * HasEdgeDistributions is an interface for managing edge distributions in a graph model.
 *
 * This interface provides methods and properties for working with edge distribution elements
 * targeting nodes or groups of nodes. It enables adding, removing, and managing the connectivity
 * relationships defined by `EdgeDistribution` instances.
 */
public interface HasEdgeDistributions {
    /**
     * A list of edge distributions targeting nodes or groups of nodes within a graph structure.
     *
     * Each entry in this list represents an `EdgeDistribution`, defining how edges connect to their
     * respective destinations. The `targets` property provides a flexible way to manage the connectivity
     * relationships in the graph, allowing for nodes or groups of nodes as edge targets.
     *
     * The `targets` may include individual node references (`NodeRef`) or other types of edge
     * distribution implementations, enabling comprehensive modeling of complex graph structures.
     */
    var targets: List<EdgeDistribution>

    /**
     * Adds an edge distribution to the `targets` list.
     * The provided `EdgeDistribution` defines a target for edges in a graph model,
     * which can be a node reference or a group of nodes.
     *
     * @param distribution The edge distribution to be added to the list of targets.
     */
    fun addDistribution(distribution: EdgeDistribution) {
        targets += distribution
    }

    /**
     * Removes the specified edge distribution from the list of targets.
     *
     * This method allows for managing the edge distribution by removing a specific
     * `EdgeDistribution` instance from the `targets` list. The `targets` list represents
     * connections or relationships in a graph model, and this method updates it to reflect
     * the removal of the specified distribution.
     *
     * @param distribution The edge distribution to be removed from the list of targets.
     */
    fun removeDistribution(distribution: EdgeDistribution) {
        targets -= distribution
    }
}
