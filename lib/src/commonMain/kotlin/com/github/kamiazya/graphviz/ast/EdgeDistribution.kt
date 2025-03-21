package com.github.kamiazya.graphviz.ast

/**
 * Represents an interface for defining edge distribution elements in a DOT graph.
 *
 * Edge distribution refers to the representation of edge targets in a graph,
 * which can be either individual node references or groups of node references.
 *
 * Implementations of this interface can model various structures such as:
 * - A single node reference, including optional port and compass information
 * - A group of node references, such as clusters or aggregates
 * - Targets for graph edges, describing source and destination relationships
 *
 * This interface is typically used to unify different types of edge-related components
 * in the Abstract Syntax Tree (AST) of a graph structure.
 */
interface EdgeDistribution : AST
