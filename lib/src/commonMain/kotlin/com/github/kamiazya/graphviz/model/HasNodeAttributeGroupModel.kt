package com.github.kamiazya.graphviz.model

/**
 * Interface representing a model that has a group of node attributes.
 *
 * The `HasNodeAttributeGroupModel` interface provides a contract for associating
 * a specific `NodeAttributeGroupModel` with an implementing entity. This group
 * encapsulates the attributes related to a node, which can be used for defining
 * properties or configurations of the node in a graph structure.
 *
 * Features:
 * - Declares a read-only property `nodeAttributes` of type `NodeAttributeGroupModel`.
 * - Serves as a building block for entities that handle or incorporate attributes of nodes.
 */
public interface HasNodeAttributeGroupModel {
    /**
     * Represents a group of attributes associated with a node.
     *
     * The `nodeAttributes` property holds a `NodeAttributeGroupModel` instance,
     * which encapsulates the attributes for a node within a graph model. These
     * attributes define various properties and configurations specific to the
     * node.
     *
     * This property is typically used in classes implementing the `HasNodeAttributeGroupModel`
     * interface and provides a structured way to manage node-related attributes.
     */
    val nodeAttributes: NodeAttributeGroupModel
}
