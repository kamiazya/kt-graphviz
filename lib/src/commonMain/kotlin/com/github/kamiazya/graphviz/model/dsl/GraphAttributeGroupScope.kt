package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.GraphAttributeGroupModel

/**
 * A scoped class that delegates its implementation to a `GraphAttributeGroupModel`.
 * This scope is typically used for organizing and managing graph attribute groups
 * with additional DSL support when defining graph structures.
 *
 * By delegating to the `GraphAttributeGroupModel`, it inherits functionality for
 * handling graph attribute groups, including attribute assignment and transformation
 * into the Abstract Syntax Tree (AST) for graph representation.
 *
 * @constructor Initializes this scope with the provided `GraphAttributeGroupModel`.
 * @param graphAttributeGroup The underlying model to delegate the graph attribute group behavior.
 */
public class GraphAttributeGroupScope(
    private val graphAttributeGroup: GraphAttributeGroupModel
) : GraphAttributeGroupModel by graphAttributeGroup
