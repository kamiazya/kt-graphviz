package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.NodeAttributeGroupModel

/**
 * Represents a scope for defining and managing node attribute groups within a Graphviz model.
 *
 * This class is a delegation-based implementation that utilizes a `NodeAttributeGroupModel` for its functionality.
 * It provides the ability to work with node-specific attributes in a Graphviz model through the delegated interface.
 *
 * @constructor Creates a `NodeAttributeGroupScope` by delegating to a `NodeAttributeGroupModel`.
 * @param nodeAttributeGroup The underlying model used for delegation and node attribute management.
 */
public class NodeAttributeGroupScope(
    private val nodeAttributeGroup: NodeAttributeGroupModel
) : NodeAttributeGroupModel by nodeAttributeGroup
