package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * A wrapper class for a DOT model that provides functionality to create and manage graphs, digraphs,
 * and related components in the DOT graph description language. The class delegates functionality
 * to the provided `DotModel` while extending it with additional creation features.
 *
 * @constructor Creates a new DotScope with the given `DotModel`.
 * @param dot The `DotModel` to delegate actions to.
 */
public class DotScope(val dot: DotModel) :
    DotModel by dot,
    CreateRootGraph,
    CreateNode,
    CreateEdge,
    CreateSubgraph,
    CreateForwardRef {
    /**
     * Creates a new graph and sets it as the root graph.
     *
     * @param id An optional ID for the graph.
     * @param comment An optional comment for the graph.
     * @param block A DSL block used to define the graph structure.
     * @return The created root graph model.
     *
     * ## Example
     *
     * If you want to create a graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * graph("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph a {
     *  color="red";
     * };
     * ```
     */
    fun graph(
        id: String? = null,
        comment: String? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.graph(id, comment, strict = false, block).also { setRootGraph(it) }

    /**
     * Creates a directed graph (digraph) and sets it as the root graph.
     *
     * @param id An optional identifier for the digraph.
     * @param comment An optional comment for the digraph.
     * @param block A DSL block used to define the structure of the digraph.
     * @return The created root graph model.
     *
     * ## Example
     *
     * If you want to create a digraph with an ID "a" and set a color of the digraph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * digraph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * digraph a {
     * color="red";
     * };
     * ```
     */
    fun digraph(
        id: String? = null,
        comment: String? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.digraph(
            id,
            comment,
            strict = false,
            block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Creates a strict directed graph (strict digraph) and sets it as the root graph.
     *
     * @param block A DSL block used to define the structure of the strict digraph.
     * @return The created root graph model configured as a strict digraph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.graph("a") {
     *   color = "red"
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
    override infix fun strict.digraph(block: RootGraphScope.() -> Unit): RootGraphModel =
        super<CreateRootGraph>.digraph(
            id = null,
            comment = null,
            strict = true,
            block = block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Creates a strict directed graph (strict digraph) and sets it as the root graph.
     *
     * @param id An identifier for the strict digraph.
     * @param comment An optional comment for the strict digraph.
     * @param block A DSL block used to define the structure of the strict digraph.
     * @return The created root graph model configured as a strict digraph.
     *
     * ## Example
     *
     * If you want to create a strict digraph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.digraph("a") {
     *   color = "red"
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
    override fun strict.digraph(
        id: String,
        comment: String?,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.digraph(
            id,
            comment,
            strict = true,
            block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Creates a strict graph and sets it as the root graph.
     *
     * @param block A DSL block used to define the structure of the strict graph.
     * @return The created root graph model configured as a strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.graph {
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
    override infix fun strict.graph(block: RootGraphScope.() -> Unit): RootGraphModel =
        super<CreateRootGraph>.graph(
            id = null,
            comment = null,
            strict = true,
            block = block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Creates a strict graph and sets it as the root graph.
     *
     * @param id An identifier for the strict graph.
     * @param comment An optional comment for the strict graph.
     * @param block A DSL block used to define the structure of the strict graph.
     * @return The created root graph model configured as a strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.graph("a") {
     *   color = "red"
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
    override fun strict.graph(
        id: String,
        comment: String?,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.graph(
            id,
            comment,
            strict = true,
            block,
        ).also {
            setRootGraph(it)
        }
}
