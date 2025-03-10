package com.github.kamiazya.graphviz.model

public interface HasEdgeDistributions {
    /**
     * A list of edge distributions.
     */
    var targets: List<EdgeDistribution>

    /**
     * Add an edge distribution.
     * @param distribution An edge distribution to add.
     */
    fun addDistribution(distribution: EdgeDistribution) {
        targets += distribution
    }

    /**
     * Remove an edge distribution.
     * @param distribution An edge distribution to remove.
     */
    fun removeDistribution(distribution: EdgeDistribution) {
        targets -= distribution
    }
}
