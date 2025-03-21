package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.EdgeAttributeGroupModel

/**
 * EdgeAttributeGroupScope serves as a concrete implementation of the EdgeAttributeGroupModel.
 * This class delegates all the functionality to the provided EdgeAttributeGroupModel instance.
 * It enables working with edge attribute groups in a structured way within the graph model DSL.
 *
 * @constructor Creates an EdgeAttributeGroupScope with the specified EdgeAttributeGroupModel.
 * @param edgeAttributeGroup The underlying EdgeAttributeGroupModel that the scope delegates to.
 */
public class EdgeAttributeGroupScope(
    private val edgeAttributeGroup: EdgeAttributeGroupModel
) : EdgeAttributeGroupModel by edgeAttributeGroup
