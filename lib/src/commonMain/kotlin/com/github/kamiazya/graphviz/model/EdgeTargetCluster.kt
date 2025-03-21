package com.github.kamiazya.graphviz.model

/**
 * Represents a cluster of edge targets in a graph model.
 *
 * EdgeTargetCluster combines functionality from the `List` and `EdgeDistribution` interfaces
 * to model a collection of edge target references (`NodeRef`) within a graph structure.
 *
 * Key Features:
 * - Inherits `List<NodeRef>`, allowing the cluster to be treated as a list of edge target references.
 * - Implements `EdgeDistribution`, enabling representation and management of edge connections
 *   for graph modeling purposes.
 *
 * This interface is utilized in graph definitions to group and manage multiple edge targets
 * in a unified manner, providing flexibility for representing clusters of nodes in the graph's
 * Abstract Syntax Tree (AST).
 */
interface EdgeTargetCluster : List<NodeRef>, EdgeDistribution
