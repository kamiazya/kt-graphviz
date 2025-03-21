package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.HasContext
import com.github.kamiazya.graphviz.model.NodeModel

/**
 * Interface representing the ability to create a node within a DOT DSL.
 * Provides functionality to define and configure nodes in a graph context.
 */
@DotDslMarker
public interface CreateNode : HasContext {
    /**
     * Creates a node within the DOT DSL.
     *
     * @param id The unique identifier for the node.
     * @param comment An optional comment associated with the node.
     * @param block A DSL block to configure the node's properties and attributes.
     * @return A NodeModel representing the created node.
     *
     * ## Example
     *
     * If you want to create a node with an ID "a" and set a color of the node to "red", you can use the following code.
     *
     * ```kotlin
     * node("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a [
     *  color="red";
     * ];
     * ```
     */
    fun node(
        id: String,
        comment: String? = null,
        block: NodeScope.() -> Unit = {}
    ): NodeModel = NodeScope(context.createNode(id = id, comment = comment)).apply(block)
}
