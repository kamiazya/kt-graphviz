package com.github.kamiazya.graphviz.model

/**
 * NodeRef represents a sealed interface for defining node references within a graph structure.
 *
 * This interface is part of the edge distribution model, extending the `EdgeDistribution` interface,
 * and is specifically designed to describe node references that act as targets or sources in graph edges.
 *
 * Key characteristics include:
 * - Integration with the abstract edge distribution model, enabling structured relationships within graphs.
 * - Flexibility for implementing various types of node references, allowing customized behavior or representation.
 * - Capability to participate in the transformation to Abstract Syntax Tree (AST) representations as part of
 *   the `EdgeDistribution` hierarchy.
 */
public sealed interface NodeRef : EdgeDistribution
