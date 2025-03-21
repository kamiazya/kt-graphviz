package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.HasGraphAttributes

/**
 * Represents a group of attributes for a graph within a DOT DSL context.
 *
 * This interface extends [HasGraphAttributes] to provide functionality for applying graph-specific attributes
 * within the scope of a DOT graph representation.
 * The attributes configured using this interface will correspond to the graph attributes defined in a generated DOT file.
 */
@DotDslMarker
interface ApplyGroupAttributeGroup : HasGraphAttributes {

    /**
     * Applies a block of graph-specific attribute configurations within the scope of a DOT graph.
     *
     * @param block A lambda with receiver used to define graph attributes specific to this graph context.
     *
     * ## Example
     *
     * If you want to set a color of a graph in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *   graph {
     *     color = "red"
     *   }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *  graph [
     *    color="red";
     *  ];
     * };
     * ```
     */
    fun graph(block: GraphAttributeGroupScope.() -> Unit) = GraphAttributeGroupScope(graphAttributes).block()
}