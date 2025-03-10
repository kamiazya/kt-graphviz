package com.github.kamiazya.graphviz.model

/**
 * SubgraphModel is an interface for subgraph models.
 */
public interface SubgraphModel : BaseGraphModel {
    fun isCluster(): Boolean = id?.startsWith("cluster_") ?: false
}
