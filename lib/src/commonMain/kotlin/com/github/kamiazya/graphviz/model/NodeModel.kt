package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder
import com.github.kamiazya.graphviz.type.Compass

/**
 * NodeModel is an interface for node models.
 */
public interface NodeModel : Model<GraphSTMT>, HasID, HasComment, HasAttributes, NodeAttributeGroup {
    /**
     * Convert to a forward reference of node.
     *
     * @param port A port of the node.
     * @param compass A compass of the node.
     * @return A ForwardRefNode.
     */
    public fun toRef(port: String? = null, compass: Compass? = null): ForwardRefNode = ForwardRefNode(id, port, compass)

    override fun toAST() = GraphSTMTBuilder {
        comment?.let {
            comment(it)
        }
        node(id.quated()) {
            for (attr in attributes) {
                load(attr.toAST())
            }
        }
    }.stmts
}
