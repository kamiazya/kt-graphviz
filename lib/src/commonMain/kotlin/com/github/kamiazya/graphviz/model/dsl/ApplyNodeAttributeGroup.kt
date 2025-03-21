package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.HasNodeAttributeGroupModel

/**
 * Represents a DSL interface that provides functionality to apply attributes to graph nodes.
 *
 * This interface allows the application of a block that defines attributes for nodes in a graph
 * through a declarative syntax.
 */
@DotDslMarker
public interface ApplyNodeAttributeGroup : HasNodeAttributeGroupModel {
    /**
     * Applies a block of attributes to a node within a DSL context.
     *
     * @param block A lambda DSL block with a receiver of type NodeAttributeGroupScope
     *              to define attributes for a node.
     *
     * ## Example
     *
     * If you want to set a color of a node in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *   node {
     *     color = "red"
     *   }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *  node [
     *    color="red";
     *  ];
     * };
     * ```
     */
    fun node(block: NodeAttributeGroupScope.() -> Unit) = NodeAttributeGroupScope(nodeAttributes).block()
}
