package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.EdgeDistribution as EdgeDistributionAST

/**
 * EdgeDistribution represents a sealed interface for defining edge distribution elements used in a DOT graph.
 *
 * This interface models how edges are connected to their targets within a graph structure
 * and allows for various implementations that represent individual or grouped edge targets.
 * It supports transformations to the Abstract Syntax Tree (AST) representation via the `Model` interface.
 *
 * Key features include:
 * - Unification of edge-related components in a graph.
 * - Enabling distinct target types such as single node references or groups of nodes.
 * - Structuring relationships between edge sources and destinations.
 */
sealed interface EdgeDistribution : Model<EdgeDistributionAST>
