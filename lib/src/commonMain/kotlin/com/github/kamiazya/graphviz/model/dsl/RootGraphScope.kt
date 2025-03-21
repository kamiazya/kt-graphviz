package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * `RootGraphScope` represents the scope for configuring and interacting with a root graph structure.
 * It provides functionalities for managing the root graph's nodes, edges, subgraphs, attributes, and
 * their associated configurations. It inherits behavior from `BaseGraphScope` and delegates properties
 * and methods to an implementation of `RootGraphModel`.
 *
 * @constructor Creates a new instance of `RootGraphScope` with the specified root graph model.
 * @param rootGraph The root graph model implementation.
 */
public class RootGraphScope(
    private val rootGraph: RootGraphModel
) :
    BaseGraphScope<RootGraphModel>,
    RootGraphModel by rootGraph
