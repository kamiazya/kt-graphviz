package com.github.kamiazya.graphviz.model

/**
 * HasNodes is an interface for models that have nodes.
 */
public interface HasNodes {
    /**
     * A list of nodes.
     */
    var nodes: List<NodeModel>

    /**
     * Get a node by ID.
     *
     * @param id An ID of the node.
     * @return A node if found.
     * @throws IllegalArgumentException if not found.
     */
    fun getNode(id: String): NodeModel = getNodeOrNull(id) ?: error("Node not found: $id")

    /**
     * Get a node by ID or null.
     *
     * @param id An ID of the node.
     * @return A node if found.
     *       null if not found.
     */
    fun getNodeOrNull(id: String): NodeModel? = nodes.find { it.id == id }

    /**
     * Add a node.
     * @param node A node to add.
     */
    fun addNode(node: NodeModel) {
        nodes += node
    }

    /**
     * Remove a node.
     * @param node A node to remove.
     */
    fun removeNode(node: NodeModel) {
        nodes -= node
    }

    /**
     * Clear all nodes.
     */
    fun clearNodes() {
        nodes = emptyList()
    }
}
