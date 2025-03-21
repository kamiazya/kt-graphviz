package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeValueOf
import com.github.kamiazya.graphviz.model.HasAttributes
import com.github.kamiazya.graphviz.model.EdgeAttributeGroup as EdgeAttributeGroupModel

/**
 * Represents a group of attributes associated with an edge in a graph.
 *
 * The `EdgeAttributeGroup` class provides a structured way to manage and assign attributes
 * to an edge by incorporating functionality from both the `EdgeAttributeGroupModel` and
 * `HasAttributes` interfaces. Attributes allow customization and specification of
 * edge properties such as appearance, behavior, or identifiers in graph structures.
 *
 * This class provides a default implementation for storing and accessing attributes
 * as a list of key-value pairs (`Attribute`), where specific attributes can be directly
 * mapped and accessed using property delegation.
 */
open class EdgeAttributeGroup : EdgeAttributeGroupModel, HasAttributes {
    override var attributes: List<Attribute> = emptyList()

    override var color: String? by AttributeValueOf()
}
