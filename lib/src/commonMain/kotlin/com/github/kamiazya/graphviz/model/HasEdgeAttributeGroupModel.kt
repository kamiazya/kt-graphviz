package com.github.kamiazya.graphviz.model

/**
 * Represents a model that includes edge attribute group information.
 *
 * This interface defines a contract for accessing a group of edge-specific attributes
 * through the `edgeAttributes` property. It allows integration and interaction with
 * `EdgeAttributeGroupModel`, which facilitates the definition and management of
 * edge-related attributes in a graph structure.
 */
interface HasEdgeAttributeGroupModel {
    /**
     * Represents a group of attributes specific to edges in a graph structure.
     *
     * This property provides access to an instance of `EdgeAttributeGroupModel`,
     * which facilitates the management and definition of attribute groups for edges.
     * It can include various edge-specific attributes such as style, color, and custom
     * key-value property pairs.
     *
     * The `edgeAttributes` property is commonly used in graph modeling frameworks
     * to configure and organize attributes associated with edges.
     */
    val edgeAttributes: EdgeAttributeGroupModel
}
