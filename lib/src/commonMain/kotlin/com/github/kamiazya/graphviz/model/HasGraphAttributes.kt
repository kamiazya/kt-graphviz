package com.github.kamiazya.graphviz.model

/**
 * Represents the capability of an entity to possess graph-specific attributes within a graph model.
 *
 * This interface provides access to the `graphAttributes` property, which encapsulates a group
 * of attributes specific to a graph's definition and behavior. The attributes are managed
 * through an instance of `GraphAttributeGroupModel`, allowing for retrieval and manipulation
 * of these graph-level characteristics.
 *
 * This can be useful in graph modeling scenarios where additional metadata or properties
 * need to be associated with the graph as a whole.
 */
public interface HasGraphAttributes {
    /**
     * Represents graph-specific attributes within a graph model.
     *
     * The `graphAttributes` property is an instance of `GraphAttributeGroupModel`,
     * which provides mechanisms to manage and retrieve the collection of attributes
     * specific to a graph. These attributes define settings or configurations that
     * apply at the graph level in the DOT graph structure.
     *
     * This property includes functionalities for:
     * - Managing a set of graph-level attributes.
     * - Converting attributes to their respective DOT graph Abstract Syntax Tree (AST) representation.
     * - Supporting customization of the graph's appearance or behavior.
     *
     * This property is utilized in contexts where graph-level customization is required,
     * offering access to attributes that control a wide range of graph properties.
     */
    val graphAttributes: GraphAttributeGroupModel
}
