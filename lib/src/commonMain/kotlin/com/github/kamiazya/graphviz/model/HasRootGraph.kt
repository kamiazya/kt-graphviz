package com.github.kamiazya.graphviz.model

/**
 * Interface for managing a root graph within a graph model.
 *
 * The `HasRootGraph` interface defines operations and properties for associating and
 * managing a root graph within a graph model. It includes methods to retrieve, set,
 * or validate the presence of the root graph. This is particularly useful in complex
 * graph representations where one graph serves as the primary or overarching structure.
 */
interface HasRootGraph {
    /**
     * The `root` variable represents the root graph associated with a graph model.
     *
     * It holds a reference to a `RootGraphModel` instance, serving as the primary entry
     * point or top-level structure for the graph model. This property can be used for
     * managing or accessing the root graph, ensuring that the graph model has a central
     * component that organizes its elements and relationships.
     *
     * Features:
     * - Nullable to allow scenarios where no root graph is set.
     * - Supports operations for retrieving, setting, and validating the presence of the root graph.
     */
    var root: RootGraphModel?

    /**
     * Retrieves the root graph associated with the current graph model.
     *
     * @return The root graph as an instance of `RootGraphModel`.
     * @throws IllegalStateException if the root graph is not set.
     */
    fun getRootGraph(): RootGraphModel = root ?: error("Root graph is not set.")

    /**
     * Retrieves the root graph associated with the current graph model, if available.
     *
     * @return The root graph as an instance of `RootGraphModel` if it is set,
     *         or `null` if no root graph is associated.
     */
    fun getRootGraphOrNull(): RootGraphModel? = root

    /**
     * Sets the root graph for the current graph model.
     *
     * This function associates a `RootGraphModel` instance to the `root` property. It ensures
     * that the root graph is only set once by checking if the `root` is already initialized.
     * If the root graph has previously been set, an exception is thrown to prevent overwriting.
     *
     * @param graph The root graph to be set, represented as an instance of `RootGraphModel`.
     * @throws IllegalStateException if the root graph is already set.
     */
    fun setRootGraph(graph: RootGraphModel) {
        check(root == null) { "Root graph is already set." }
        root = graph
    }
}
