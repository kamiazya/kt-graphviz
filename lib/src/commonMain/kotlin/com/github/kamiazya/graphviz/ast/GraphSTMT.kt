package com.github.kamiazya.graphviz.ast

/**
 * Represents a Graph Statement used in the construction of a DOT graph Abstract Syntax Tree (AST).
 *
 * A `GraphSTMT` is a sealed interface that defines various structural components of a graph such as:
 * - Nodes
 * - Edges
 * - Attributes
 * - Subgraphs
 * - Comments
 *
 * Implementations of `GraphSTMT` align with specific elements in a DOT graph's structure
 * and are used to describe their relationships and attributes in the graph hierarchy.
 *
 * This interface is commonly utilized in graph models that involve parsing or generating ASTs
 * for DOT language representations.
 */
sealed interface GraphSTMT : AST
