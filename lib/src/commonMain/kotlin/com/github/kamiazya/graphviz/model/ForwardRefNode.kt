package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.dsl.EdgeDistributionBuilder
import com.github.kamiazya.graphviz.type.Compass

/**
 * Represents a forward reference to a node in a graph, which may include optional port and compass point information.
 *
 * A `ForwardRefNode` is used to define a node reference with its identifier and optional additional attributes
 * such as port or compass directions. These references are typically used in constructing graph visualization
 * structures and defining relationships between nodes.
 *
 * @property id The unique identifier for the node.
 * @property port An optional port associated with the node, specifying subcomponents or subdivisions.
 * @property compass An optional compass direction indicating the relative position (e.g., north, south).
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
 */
data class ForwardRefNode(
    val id: String,
    val port: String? = null,
    val compass: Compass? = null,
) : NodeRef {
    companion object {

        private const val MAY_ONLY_HAVE_ID = 1
        private const val MAY_HAVE_ID_AND_PORT_OR_COMPASS = 2
        private const val MAY_HAVE_ID_AND_PORT_AND_COMPASS = 3

        /**
         * Parses a string reference into a ForwardRefNode object.
         *
         * The input string is expected to be in one of the following formats:
         * - `ID`
         * - `ID:PortOrCompass`
         * - `ID:Port:Compass`
         *
         * Based on the format, the method extracts the appropriate parts:
         * - An ID is always required.
         * - A Port is optional and is provided either with or without a Compass.
         * - A Compass is optional and interpreted when valid.
         *
         * @param ref A string reference containing the ID and optionally the Port and/or Compass.
         * @return A ForwardRefNode object constructed using the parsed ID, Port, and Compass.
         * @throws IllegalArgumentException if the format of the input string is invalid.
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
