package com.github.kamiazya.graphviz.ast

/**
 * Represents a collection of node references associated with a cluster in a DOT graph.
 *
 * This class is used to model groups of `NodeRef` elements that correspond to clusters
 * or aggregates of graph nodes, specifically for use in edge targeting or other edge-related
 * operations within a graph's Abstract Syntax Tree (AST).
 *
 * As a part of the AST, `ClusterNodeRefs` allows the representation of multiple node references
 * as a single entity, providing a way to logically group or cluster nodes together for further
 * processing or rendering in a graph structure.
 *
 * @property refs A list of `NodeRef` instances representing the nodes included in this cluster.
 */
data class ClusterNodeRefs(
    var refs: List<NodeRef>,
) : AST, EdgeDistribution
