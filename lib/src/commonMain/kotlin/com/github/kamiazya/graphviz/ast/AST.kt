package com.github.kamiazya.graphviz.ast

/**
 * Represents the base sealed interface for all Abstract Syntax Tree (AST) elements
 * used to model structures in a DOT graph representation.
 *
 * The `AST` interface is implemented by various other interfaces and classes
 * to define specific components, attributes, and relationships within the
 * graph structure.
 *
 * Classes and interfaces that extend `AST` define elements such as:
 *
 * - Attributes for nodes, edges, or graphs
 * - Comments, literal values, or attribute groups
 * - Graph structures such as root graphs or subgraphs
 * - Parent-child relationships between AST components
 *
 * This interface allows a unified type system for all elements in the DOT graph's AST,
 * enabling modularity and extensibility of its components.
 */
sealed interface AST
