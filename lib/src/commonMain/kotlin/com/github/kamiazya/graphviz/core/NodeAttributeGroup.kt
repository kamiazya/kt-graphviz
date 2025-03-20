package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeValueOf
import com.github.kamiazya.graphviz.model.HasAttributes
import com.github.kamiazya.graphviz.model.NodeAttributeGroup as NodeAttributeGroupModel

public open class NodeAttributeGroup : HasAttributes, NodeAttributeGroupModel {
    override var attributes: List<Attribute> = emptyList()

    override var shape: String? by AttributeValueOf()
    override var color: String? by AttributeValueOf()
}
