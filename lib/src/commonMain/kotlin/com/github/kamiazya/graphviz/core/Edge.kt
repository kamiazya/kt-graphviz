package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.EdgeModel

/**
 * Represents an edge in a graph model.
 *
 * The `Edge` class is used to define connections between multiple targets within a graph.
 * Each `Edge` must have at least two targets, where targets are specified using
 * the `EdgeDistribution` interface. The edge may also include an optional comment.
 *
 * This class inherits attributes and functionalities from `EdgeAttributeGroup` and implements
 * the `EdgeModel` interface, enabling integration with other graph components.
 *
 * @property targets A list of `EdgeDistribution` objects that this edge connects.
 *                   Must have a minimum of two targets.
 * @property comment An optional comment string describing or annotating the edge.
 * @throws IllegalArgumentException If fewer than two targets are provided.
 */
class Edge(
    override var targets: List<EdgeDistribution>,
    override var comment: String? = null,
) : EdgeAttributeGroup(), EdgeModel {
    /**
     * Secondary constructor for the `Edge` class.
     *
     * This constructor provides an alternative way to create an `Edge` instance
     * by passing a variable number of `EdgeDistribution` targets (vararg) directly.
     * It is useful when the number of connected targets is known at compile-time.
     * As with the primary constructor, the number of targets must be at least two.
     *
     * @param targets A variable number of `EdgeDistribution` objects that the edge connects.
     * @param comment An optional string describing or annotating the edge. Defaults to `null`.
     * @throws IllegalArgumentException If fewer than two targets are provided.
     */
    constructor(vararg targets: EdgeDistribution, comment: String? = null) : this(targets.toList(), comment)

    init {
        require(targets.size >= 2) { "Edge must have at least two targets." }
    }
}
