package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeValueOf
import com.github.kamiazya.graphviz.model.HasAttributes
import com.github.kamiazya.graphviz.model.GraphAttributeGroup as GraphAttributeGroupModel

/**
 * Represents a group of attributes for a graph.
 *
 * The `GraphAttributeGroup` class is an abstraction for grouping attributes associated with a graph.
 * It inherits basic attribute-handling operations from the `HasAttributes` interface
 * and defines common graph-specific properties from the `GraphAttributeGroupModel` interface.
 *
 * This class provides functionality to work with graph attributes, including setting
 * or retrieving individual attributes, managing the list of attributes, and handling changes
 * to specific graph-related properties such as `color`.
 */
open class GraphAttributeGroup : HasAttributes, GraphAttributeGroupModel {
    override var attributes: List<Attribute> = emptyList()

    override var color: String? by AttributeValueOf()
}
