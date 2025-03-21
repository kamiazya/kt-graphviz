package com.github.kamiazya.graphviz.model

/**
 * ModelContext is an interface that defines the blueprint for creating various components
 * of a graph model in the DOT language, including dots, graphs, subgraphs, nodes, and edges.
 * It provides a structured environment for constructing and managing graph structures
 * and their specific attributes.
 *
 * Key Responsibilities:
 * - Generate graph components such as dots, digraphs, graphs, subgraphs, nodes, and edges.
 * - Maintain contextual integrity and relationships between graph elements.
 * - Support additional parameters like comments, identifiers, contexts, and flags to
 *   customize the created graph objects.
 * - Facilitate the creation of graph-based hierarchical structures adhering to DOT specifications.
 */
public interface ModelContext {
    /**
     * Creates a DOT model representation based on the given parameters.
     *
     * @param comment An optional comment to be included in the DOT model.
     * @param context The context in which the DOT model is created. Defaults to the current context.
     * @param root An optional root graph model to associate with the DOT model.
     * @return A new instance of DotModel representing the constructed DOT graph.
     */
    fun createDot(
        comment: String? = null,
        context: ModelContext = this,
        root: RootGraphModel? = null,
    ): DotModel

    /**
     * Creates a directed graph model (digraph) with the specified parameters.
     *
     * @param strict Specifies whether the graph should be strict, disallowing parallel edges.
     * @param id An optional identifier for the graph.
     * @param comment An optional comment to add metadata or context to the graph.
     * @return A new instance of `RootGraphModel` representing the directed graph.
     */
    fun createDigraph(
        strict: Boolean = false,
        id: String? = null,
        comment: String? = null,
    ): RootGraphModel

    /**
     * Creates a general graph model with the specified parameters.
     *
     * @param strict Specifies whether the graph should be strict, disallowing parallel edges. Defaults to `false`.
     * @param id An optional identifier for the graph. Defaults to `null`.
     * @param comment An optional comment to add metadata or context to the graph. Defaults to `null`.
     * @return A new instance of `RootGraphModel` representing the constructed graph.
     */
    fun createGraph(
        strict: Boolean = false,
        id: String? = null,
        comment: String? = null,
    ): RootGraphModel

    /**
     * Creates a subgraph model with the specified parameters.
     *
     * @param id An optional identifier for the subgraph. Defaults to `null`.
     * @param comment An optional comment providing descriptive metadata for the subgraph. Defaults to `null`.
     * @return A new instance of `SubgraphModel` representing the created subgraph.
     */
    fun createSubgraph(id: String? = null, comment: String? = null): SubgraphModel

    /**
     * Creates a node model with the specified identifier and optional comment.
     *
     * This method constructs an instance of `NodeModel`, representing a node in a graph.
     * The node is uniquely identified by the provided `id` and can optionally include
     * a comment for descriptive metadata or context.
     *
     * @param id The unique identifier of the node.
     * @param comment An optional comment providing additional metadata about the node. Defaults to `null`.
     * @return A new instance of `NodeModel` representing the created node.
     */
    fun createNode(id: String, comment: String? = null): NodeModel

    /**
     * Creates an edge model that represents a relationship or connection in a graph structure.
     *
     * @param targets A list of `EdgeDistribution` elements specifying the destinations for the edge.
     *                Each `EdgeDistribution` determines how the edge connects to target nodes or groups.
     * @param comment An optional comment providing additional metadata about the edge.
     *                Defaults to `null` if not provided.
     * @return An instance of `EdgeModel` representing the constructed edge with specified targets and comment.
     */
    fun createEdge(targets: List<EdgeDistribution>, comment: String? = null): EdgeModel

    companion object {
        var default: ModelContext? = null
    }
}
