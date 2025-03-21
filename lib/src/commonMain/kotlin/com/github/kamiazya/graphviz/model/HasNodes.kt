package com.github.kamiazya.graphviz.model

/**
 * Interface defining the capability of managing nodes in a graph model.
 *
 * The `HasNodes` interface provides methods for maintaining a collection of node entities and offers
 * operations such as retrieving, adding, removing, and clearing nodes. It is typically used in graph-related
 * models to manage the representation of nodes and their relationships with other graph components.
 *
 * Features:
 * - Maintains a list of `NodeModel` instances for node management.
 * - Supports retrieving nodes by ID with error handling or null-based checks.
 * - Allows addition and removal of individual nodes.
 * - Provides functionality to clear all nodes.
 */
public interface HasNodes {
    /**
     * A list containing all nodes managed by the implementing class.
     *
     * This property holds instances of `NodeModel`, representing individual nodes within a graph model.
     * It provides storage for node entities and allows for operations such as addition, removal, and retrieval.
     * The `nodes` property ensures direct access to the collection of nodes for further manipulation or query
     * operations.
     *
     * Modifications to this property dynamically affect the structure of the graph, enabling flexible node management.
     */
    var nodes: List<NodeModel>

    /**
     * Retrieves a node by its ID.
     *
     * This method attempts to fetch a node identified by the specified ID. If the node
     * is not found, an exception is thrown with a message indicating that the node
     * could not be located.
     *
     * @param id The unique identifier of the node to retrieve.
     * @return The corresponding node, if found.
     * @throws IllegalArgumentException If no node with the specified ID is found.
     */
    fun getNode(id: String): NodeModel = getNodeOrNull(id) ?: error("Node not found: $id")

    /**
     * Retrieves a node by its ID or returns null if not found.
     *
     * This method searches for a node within the current collection of nodes
     * using the provided ID. If a node with the specified ID is found,
     * it is returned. Otherwise, the method returns null, indicating that
     * no matching node exists.
     *
     * @param id The unique identifier of the node to find.
     * @return The matching node instance if found, otherwise null.
     */
    fun getNodeOrNull(id: String): NodeModel? = nodes.find { it.id == id }

    /**
     * Adds a node to the collection of nodes.
     *
     * This method appends the provided node instance to the internal collection
     * of nodes. It is used to dynamically grow the set of nodes managed within
     * this class.
     *
     * @param node The node to be added, represented as an instance of `NodeModel`.
     */
    fun addNode(node: NodeModel) {
        nodes += node
    }

    /**
     * Removes a node from the collection of nodes.
     *
     * This method removes the specified node from the internal collection of nodes
     * managed by the instance of the class. If the node exists in the collection,
     * it will be removed. No action is taken if the node is not present.
     *
     * @param node The node to be removed, represented as an instance of `NodeModel`.
     */
    fun removeNode(node: NodeModel) {
        nodes -= node
    }

    /**
     * Clears the collection of nodes.
     *
     * This method resets the `nodes` property to an empty list, effectively
     * removing all existing nodes from the collection. It is typically used
     * to reset or clean up the nodes managed by the class.
     */
    fun clearNodes() {
        nodes = emptyList()
    }
}
