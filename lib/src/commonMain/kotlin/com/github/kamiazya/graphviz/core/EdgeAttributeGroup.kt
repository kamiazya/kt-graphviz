package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeValueOf
import com.github.kamiazya.graphviz.model.HasAttributes
import com.github.kamiazya.graphviz.model.EdgeAttributeGroup as EdgeAttributeGroupModel

public open class EdgeAttributeGroup : EdgeAttributeGroupModel, HasAttributes {
    override var attributes: List<Attribute> = emptyList()

    override var color: String? by AttributeValueOf()
}
