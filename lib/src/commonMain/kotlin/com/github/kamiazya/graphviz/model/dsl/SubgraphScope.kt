package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.SubgraphModel

/**
 * Represents the scope for constructing and configuring a subgraph within a graph structure.
 *
 * This class extends the `BaseGraphScope` to provide additional functionality specific
 * to manipulating subgraphs. It also delegates the implementation of `SubgraphModel`,
 * enabling constructed subgraphs to retain all the properties and methods defined in the
 * subgraph model.
 *
 * @constructor Creates a `SubgraphScope` with the specified subgraph model.
 * @param subgraph The model representing the subgraph to be configured within this scope.
 */
public class SubgraphScope(
    private val subgraph: SubgraphModel
) :
    BaseGraphScope<SubgraphModel>,
    SubgraphModel by subgraph
