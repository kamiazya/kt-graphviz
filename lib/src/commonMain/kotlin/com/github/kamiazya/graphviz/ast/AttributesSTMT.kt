package com.github.kamiazya.graphviz.ast

/**
 * Represents a marker interface for all attribute-related statements within the Abstract Syntax Tree (AST)
 * of a DOT graph.
 *
 * Classes implementing this interface define specific attribute-related elements, such as:
 * - Single attributes that define key-value pairs.
 * - Groups of attributes categorized by types like graph, node, or edge.
 * - Comments associated with the attributes.
 *
 * This interface allows attribute-related elements to be distinguished and grouped within the greater structure
 * of a DOT graph representation.
 */
sealed interface AttributesSTMT : AST
