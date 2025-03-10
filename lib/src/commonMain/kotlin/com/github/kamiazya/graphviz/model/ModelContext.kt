package com.github.kamiazya.graphviz.model

/**
 * ModelContext is a context for creating models.
 */
public interface ModelContext {
    /**
     * Create a dot.
     *
     * @param comment A comment of the dot.
     * @param context A context of the dot.
     * @param root A root graph of the dot.
     * @param block A block to create a dot.
     * @return A dot.
     */
    fun createDot(
        comment: String? = null,
        context: ModelContext = this,
        root: RootGraphModel? = null,
    ): DotModel

    /**
     * Create a digraph.
     * @param strict A strict flag.
     * @param id An ID of the digraph.
     * @param block A block to create a digraph.
     * @return A digraph.
     */
    fun createDigraph(
        strict: Boolean = false,
        id: String? = null,
        comment: String? = null,
    ): RootGraphModel

    /**
     * Create a graph.
     * @param strict A strict flag.
     * @param id An ID of the graph.
     * @param block A block to create a graph.
     * @return A graph.
     */
    fun createGraph(
        strict: Boolean = false,
        id: String? = null,
        comment: String? = null,
    ): RootGraphModel

    /**
     * Create a subgraph.
     * @param id An ID of the subgraph.
     * @param comment A comment of the subgraph.
     * @param block A block to create a subgraph.
     * @return A subgraph.
     */
    fun createSubgraph(id: String? = null, comment: String? = null): SubgraphModel

    /**
     * Create a node.
     * @param id An ID of the node.
     * @param comment A comment of the node.
     * @param block A block to create a node.
     * @return A node.
     */
    fun createNode(id: String, comment: String? = null): NodeModel

    /**
     * Create an edge.
     * @param targets A list of edge distributions.
     * @param comment A comment of the edge.
     * @param block A block to create an edge.
     * @return An edge.
     */
    fun createEdge(targets: List<EdgeDistribution>, comment: String? = null): EdgeModel

    companion object {
        var default: ModelContext? = null
    }
}
