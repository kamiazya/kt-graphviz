package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.NodeModel

/**
 * Node is a class for node models.
 */
class Node(
    override var id: String,
    override var comment: String? = null,
) : NodeAttributeGroup(), NodeModel
