package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeValueOf
import com.github.kamiazya.graphviz.model.HasAttributes
import com.github.kamiazya.graphviz.model.NodeAttributeGroup as NodeAttributeGroupModel

/**
 * Represents a group of node attributes in a graph model.
 *
 * The `NodeAttributeGroup` class provides a structure for defining and managing
 * attributes associated with a node in a graph. It combines attributes defined
 * in the `HasAttributes` interface and the `NodeAttributeGroupModel` interface,
 * offering properties for customization such as `shape` and `color`.
 *
 * This class allows direct interaction with attributes both as a list and as
 * individual properties using delegated values.
 */
open class NodeAttributeGroup : HasAttributes, NodeAttributeGroupModel {
    override var attributes: List<Attribute> = emptyList()

    override var shape: String? by AttributeValueOf()
    override var color: String? by AttributeValueOf()
}
