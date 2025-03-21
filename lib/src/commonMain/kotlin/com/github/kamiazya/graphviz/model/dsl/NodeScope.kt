package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.NodeModel

/**
 * NodeScope is a delegate for `NodeModel` that provides a scoped context for working with a node.
 *
 * This class enables direct access to all functionalities of the `NodeModel` it wraps, along with
 * any additional behavior or properties specific to the `NodeScope` itself.
 *
 * @param node The `NodeModel` instance to delegate to.
 */
public class NodeScope(
    private val node: NodeModel
) : NodeModel by node
