package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeValueOf
import com.github.kamiazya.graphviz.model.HasAttributes
import com.github.kamiazya.graphviz.model.GraphAttributeGroup as GraphAttributeGroupModel

public open class GraphAttributeGroup : HasAttributes, GraphAttributeGroupModel {
    override var attributes: List<Attribute> = emptyList()

    override var color: String? by AttributeValueOf()
}
