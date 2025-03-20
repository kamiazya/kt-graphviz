package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.EdgeModel

/**
 * Edge is a class for edge models.
 *
 * @param targets List of [EdgeDistribution] objects.
 * @param comment Comment string.
 *
 * @throws IllegalArgumentException If the number of targets is less than 2.
 */
class Edge(
    override var targets: List<EdgeDistribution>,
    override var comment: String? = null,
) : EdgeAttributeGroup(), EdgeModel {
    constructor(vararg targets: EdgeDistribution, comment: String? = null) : this(targets.toList(), comment)

    init {
        require(targets.size >= 2) { "Edge must have at least two targets." }
    }
}
