package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.HasContext
import com.github.kamiazya.graphviz.model.SubgraphModel

/**
 * Interface representing the ability to create and manage subgraphs within a DOT DSL context.
 * Provides methods to define subgraphs and their associated attributes using a DSL-friendly approach.
 */
@DotDslMarker
public interface CreateSubgraph : HasContext {
    /**
     * Creates a subgraph with the given parameters and applies a DSL block to it.
     *
     * @param id An optional identifier for the subgraph.
     * @param comment An optional comment associated with the subgraph.
     * @param block A DSL block used to define the subgraph's attributes and contents.
     * @return The constructed subgraph model.
     *
     * ## Example
     *
     * If you want to create a subgraph with an ID "a" and set a color of the subgraph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * subgraph("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * subgraph a {
     *   color="red";
     * };
     * ```
     */
    fun subgraph(
        id: String? = null,
        comment: String? = null,
        block: SubgraphScope.() -> Unit,
    ): SubgraphModel =
        SubgraphScope(context.createSubgraph(id = id, comment = comment)).apply(block)
}
