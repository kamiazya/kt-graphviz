package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.HasContext
import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * Interface for creating root-level graph models with various configurations.
 * Provides methods to create simple graphs, directed graphs (digraphs), and strict versions
 * of these graphs in a DSL-like approach. The created graphs are defined with optional
 * parameters and a configuration block.
 */
@DotDslMarker
public interface CreateRootGraph : HasContext {
    /**
     * Creates a graph with optional parameters and configuration block.
     *
     * @param id An optional identifier for the graph.
     * @param comment An optional comment to describe the graph.
     * @param strict An optional flag indicating whether the graph is strict.
     * @param block A configuration block for defining the graph structure and attributes.
     * @return The created graph model.
     *
     * ## Example
     *
     * If you want to create a graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * graph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph a {
     *   color="red";
     * };
     * ```
     */
    fun graph(
        id: String? = null,
        comment: String? = null,
        strict: Boolean? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel = RootGraphScope(context.createGraph(strict = false, id = id, comment = comment)).apply(block)

    /**
     * Creates a directed graph (digraph) with optional parameters and a configuration block.
     *
     * @param id An optional identifier for the digraph.
     * @param comment An optional comment to describe the digraph.
     * @param strict An optional flag indicating whether the digraph is strict.
     * @param block A configuration block for defining the digraph structure and attributes.
     * @return The created digraph model.
     *
     * ## Example
     *
     * If you want to create a graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * graph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph a {
     *   color="red";
     * };
     * ```
     */
    fun digraph(
        id: String? = null,
        comment: String? = null,
        strict: Boolean? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel = RootGraphScope(context.createDigraph(strict = false, id = id, comment = comment)).apply(block)

    /**
     * Creates a strict directed graph (digraph) using a DSL configuration block.
     *
     * @param block A configuration block for defining the structure and attributes of the strict digraph.
     * @return A model representing the strict directed graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict digraph {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict digraph {
     *  color="red";
     * };
     * ```
     */
    infix fun strict.digraph(block: RootGraphScope.() -> Unit): RootGraphModel =
        RootGraphScope(context.createDigraph(strict = true)).apply(block)

    /**
     * Creates a strict directed graph (digraph) using a DSL configuration block.
     *
     * @param id An identifier for the strict digraph.
     * @param comment An optional comment describing the strict digraph.
     * @param block A configuration block for defining the structure and attributes of the strict digraph.
     * @return A model representing the strict directed graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.digraph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict digraph a {
     *   color="red";
     * };
     * ```
     */
    fun strict.digraph(
        id: String,
        comment: String? = null,
        block: RootGraphScope.() -> Unit,
    ): RootGraphModel =
        RootGraphScope(context.createDigraph(strict = true, id = id, comment = comment)).apply(block)

    /**
     * Creates a strict graph using a DSL configuration block.
     *
     * @param block A configuration block for defining the structure and attributes of the strict graph.
     * @return A model representing the strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     *
     * ```kotlin
     * strict graph {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict graph {
     *   color="red";
     * };
     * ```
     */
    infix fun strict.graph(block: RootGraphScope.() -> Unit): RootGraphModel =
        RootGraphScope(context.createGraph(strict = true)).apply(block)

    /**
     * Creates a strict graph using the specified parameters and a configuration block.
     *
     * @param id An identifier for the strict graph.
     * @param comment An optional comment describing the strict graph.
     * @param block A configuration block for defining the structure and attributes of the strict graph.
     * @return A model representing the strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     *
     * ```kotlin
     * strict.graph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict graph a {
     *   color="red";
     * };
     * ```
     */
    fun strict.graph(
        id: String,
        comment: String? = null,
        block: RootGraphScope.() -> Unit,
    ): RootGraphModel =
        RootGraphScope(context.createGraph(strict = true, id = id, comment = comment)).apply(block)
}
