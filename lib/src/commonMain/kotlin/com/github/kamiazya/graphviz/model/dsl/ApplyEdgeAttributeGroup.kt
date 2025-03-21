package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.HasEdgeAttributeGroupModel

/**
 * Represents an interface for applying a group of edge attributes in a graph DSL.
 * This interface enables the customization of edge attributes for graph elements.
 */
@DotDslMarker
public interface ApplyEdgeAttributeGroup : HasEdgeAttributeGroupModel {
    /**
     * Defines a group of edge attributes to configure the properties of edges in a graph.
     *
     * @param block A lambda with receiver of type [EdgeAttributeGroupScope] to configure edge attributes.
     *
     * ## Example
     *
     * If you want to set a color of an edge in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *    edge {
     *      color = "red"
     *    }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *   edge [
     *     color="red";
     *   ];
     * };
     * ```
     */
    fun edge(block: EdgeAttributeGroupScope.() -> Unit) = EdgeAttributeGroupScope(edgeAttributes).block()
}
