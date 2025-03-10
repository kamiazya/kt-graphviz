package com.github.kamiazya.graphviz.model

/**
 * EdgeTargetList is a collection of edge targets.
 */
class EdgeTargetList(
    private val targets: MutableList<NodeRef>
) : EdgeTargetCluster, MutableList<NodeRef> by targets {
    constructor(vararg targets: NodeRef) : this(targets.toMutableList())
}
