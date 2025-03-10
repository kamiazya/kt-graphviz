package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.type.Compass

/**
 * NodeModel is an interface for node models.
 */
public interface NodeModel : HasID, HasComment, HasAttributes, NodeAttributeGroupModel, NodeRef {
    /**
     * Convert to a forward reference of node.
     *
     * @param port A port of the node.
     * @param compass A compass of the node.
     * @return A ForwardRefNode.
     */
    public fun toRef(port: String? = null, compass: Compass? = null): ForwardRefNode = ForwardRefNode(id, port, compass)
}
