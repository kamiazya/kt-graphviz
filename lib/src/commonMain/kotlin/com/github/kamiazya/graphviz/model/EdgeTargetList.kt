package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.dsl.EdgeDistributionBuilder

/**
 * EdgeTargetList is a collection of edge targets.
 */
class EdgeTargetList(
    private val targets: MutableList<NodeRef>
) : EdgeTargetCluster, MutableList<NodeRef> by targets {
    constructor(vararg targets: NodeRef) : this(targets.toMutableList())

    override fun toAST() = EdgeDistributionBuilder {
        // targets.forEach { nodeRef(it) }
    }.stmts
}
