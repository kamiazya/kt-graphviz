package com.github.kamiazya.graphviz.ast

/**
 * Represents an attribute key-value pair in a DOT graph Abstract Syntax Tree (AST).
 *
 * The `Attribute` class models an individual attribute consisting of a key and a value,
 * both represented as `Literal` instances. Attributes are typically used to define
 * properties or metadata for graph components such as nodes, edges, or graphs.
 *
 * This class implements the `AST`, `AttributesSTMT`, and `GraphSTMT` interfaces, making
 * it a part of both the attribute-specific and general graph-related statement hierarchies.
 *
 * @property key The key of the attribute, represented as a `Literal`.
 * @property value The value associated with the key, represented as a `Literal`.
 */
data class Attribute(
    var key: Literal,
    var value: Literal,
) : AST, AttributesSTMT, GraphSTMT
