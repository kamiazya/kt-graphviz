package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.dsl.EdgeDistributionBuilder
import com.github.kamiazya.graphviz.type.Compass

/**
 * Represents a forward reference node in a graph.
 *
 * This class is used to represent a node that is referenced before it is defined in the graph.
 * It allows creating graphs with circular dependencies or references to nodes that are defined later.
 *
 * @property id An ID of the node.
 * @property port A port of the node.
 * @property compass A compass of the node
 *
 * ## Examples
 *
 * ### simple case
 *
 * ```kotlin
 * val ref = ForwardRefNode("a")
 * ref.id // "a"
 * ref.port // null
 * ref.compass // null
 * ```
 *
 * ### with port
 *
 * ```kotlin
 * val ref = ForwardRefNode("a", "port")
 * ref.id // "a"
 * ref.port // "port"
 * ref.compass // null
 * ```
 *
 * ### with compass
 *
 * ```kotlin
 * val ref = ForwardRefNode("a", compass = Compass.N)
 * ref.id // "a"
 * ref.port // null
 * ref.compass // Compass.N
 * ```
 *
 * ### with port and compass
 *
 * ```kotlin
 * val ref = ForwardRefNode("a", "port", Compass.N)
 * ref.id // "a"
 * ref.port // "port"
 * ref.compass // Compass.N
 * ```
 *
 * ### from string
 *
 * ```kotlin
 * val ref = ForwardRefNode.from("a:port:n")
 * ref.id // "a"
 * ref.port // "port"
 * ref.compass // Compass.N
 * ```
 *
 * ## Usage
 *
 * ```kotlin
 * val ref = ForwardRefNode("a")
 * val ref2 = ForwardRefNode("b", "port")
 * val ref3 = ForwardRefNode("c", compass = Compass.N)
 * val ref4 = ForwardRefNode.from("a:port:n")
 *
 * val edge = Edge(listOf(ref, ref2, ref3, ref4))
 * ```
 *
 */
public data class ForwardRefNode(
    val id: String,
    val port: String? = null,
    val compass: Compass? = null,
) : NodeRef {
    companion object {

        internal const val MAY_ONLY_HAVE_ID = 1
        internal const val MAY_HAVE_ID_AND_PORT_OR_COMPASS = 2
        internal const val MAY_HAVE_ID_AND_PORT_AND_COMPASS = 3

        /**
         * Create a ForwardRefNode from a string.
         * @param ref A string to create a ForwardRefNode.
         * @return A ForwardRefNode.
         * @throws IllegalArgumentException if the ref is invalid.
         *
         * ## Examples
         *
         * ### simple case
         * ```kotlin
         * val ref = ForwardRefNode.from("a")
         * ref.id // "a"
         * ref.port // null
         * ref.compass // null
         * ```
         *
         * ### with port
         * ```kotlin
         * val ref = ForwardRefNode.from("a:port")
         * ref.id // "a"
         * ref.port // "port"
         * ref.compass // null
         * ```
         *
         * ### with compass
         *
         * ```kotlin
         * val ref = ForwardRefNode.from("a:n")
         * ref.id // "a"
         * ref.port // null
         * ref.compass // Compass.N
         * ```
         *
         * ### with port and compass
         *
         * ```kotlin
         * val ref = ForwardRefNode.from("a:port:n")
         * ref.id // "a"
         * ref.port // "port"
         * ref.compass // Compass.N
         * ```
         *
         * ## Usage
         *
         * ```kotlin
         * val ref = ForwardRefNode.from("a")
         *
         * val ref2 = ForwardRefNode.from("b:port")
         *
         * val ref3 = ForwardRefNode.from("c:n")
         *
         * val ref4 = ForwardRefNode.from("d:port:n")
         *
         * val edge = Edge(ref, ref2, ref3, ref4)
         * ```
         */
        fun from(ref: String): ForwardRefNode {
            val (id, port, compass) = ref.split(":").let {
                when (it.size) {
                    MAY_ONLY_HAVE_ID -> {
                        val id = it[0]
                        Triple(id, null, null)
                    }
                    MAY_HAVE_ID_AND_PORT_OR_COMPASS -> {
                        val (id, portOrCompass) = it
                        try {
                            Triple(id, null, Compass.from(portOrCompass))
                        } catch (@Suppress("SwallowedException") e: IllegalArgumentException) {
                            Triple(id, portOrCompass, null)
                        }
                    }
                    MAY_HAVE_ID_AND_PORT_AND_COMPASS -> {
                        val (id, port, compass) = it
                        Triple(id, port, Compass.from(compass))
                    }
                    else -> throw IllegalArgumentException("Invalid ref: $ref")
                }
            }
            return ForwardRefNode(id, port, compass)
        }
    }

    override fun toAST() = EdgeDistributionBuilder {
        nodeRef(
            id = literalOf(id),
            port = port?.let { literalOf(it) },
            compass = compass?.let { literalOf(it.name) }
        )
    }.stmts
}
