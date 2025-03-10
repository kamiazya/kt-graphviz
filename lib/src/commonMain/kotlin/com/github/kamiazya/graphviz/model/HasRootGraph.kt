package com.github.kamiazya.graphviz.model

/**
 * HasRootGraph is an interface for models that have a root graph.
 */
public interface HasRootGraph {
    /**
     * A root graph.
     */
    var root: RootGraphModel?

    /**
     * Get the root graph.
     * @return A root graph.
     */
    fun getRootGraph(): RootGraphModel = root ?: error("Root graph is not set.")

    /**
     * Get the root graph or null.
     *
     * @return A root graph if exists.
     *        null if not exists.
     */
    fun getRootGraphOrNull(): RootGraphModel? = root

    /**
     * Set the root graph.
     * @param graph A root graph to set.
     */
    fun setRootGraph(graph: RootGraphModel) {
        check(root == null) { "Root graph is already set." }
        root = graph
    }
}
