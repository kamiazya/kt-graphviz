package com.github.kamiazya.graphviz.model

/**
 * BaseAttributeGroup is a base class for attribute group models.
 */
public abstract class BaseAttributeGroup : AttributeGroupModel {
    override var attributes: List<Attribute> = emptyList()
}
