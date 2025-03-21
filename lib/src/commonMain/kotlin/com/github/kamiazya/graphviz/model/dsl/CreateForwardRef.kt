package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.ForwardRefNode
import com.github.kamiazya.graphviz.type.Compass

/**
 * Interface representing the creation of forward references in a graph.
 *
 * This interface provides methods for creating forward references for nodes based on their ID, port,
 * and compass. Forward references allow referencing nodes before they are explicitly defined,
 * which is useful for graphs with circular dependencies or later-defined nodes.
 */
@DotDslMarker
public interface CreateForwardRef {
    /**
     * Creates a forward reference of a node based on the provided string.
     *
     * @param ref A string representing the forward reference. The string can be in the format of "id", "id:port",
     *            or "id:port:compass".
     * @return A ForwardRefNode object representing the forward reference.
     *
     * ## Example
     *
     * ### ID
     *
     * If you want to create a forward reference of a node with an ID "a",
     *
     * ```kotlin
     * ref("a")
     * ```
     *
     * ### ID and Port
     *
     * If you want to create a forward reference of a node with an ID "a" and a port "port_of_a",
     *
     * ```kotlin
     * ref("a:port_of_a")
     * ```
     *
     * ### ID, Port, and Compass
     *
     * If you want to create a forward reference of a node with an ID "a" and a port "port_of_a" and a compass "N",
     *
     * ```kotlin
     * ref("a:port_of_a:N")
     * ```
     */
    fun ref(ref: String): ForwardRefNode = ForwardRefNode.from(ref)

    /**
     * Creates a forward reference node using the specified parameters.
     *
     * @param id The unique identifier of the node.
     * @param port The port of the node, or null if no port is specified.
     * @param compass The compass direction of the node (e.g., N, NE, etc.), or null if no compass is specified.
     * @return A ForwardRefNode representing the forward reference to the node.
     *
     * ## Example
     *
     * If you want to create a forward reference of a node with an ID "a",
     *
     * ```kotlin
     * ref("a")
     * ```
     */
    fun ref(id: String, port: String? = null, compass: Compass? = null): ForwardRefNode = ForwardRefNode(
        id,
        port,
        compass
    )
}