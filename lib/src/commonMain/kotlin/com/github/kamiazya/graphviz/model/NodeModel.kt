package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder
import com.github.kamiazya.graphviz.type.Compass

/**
 * Represents a model for a node in a graph structure.
 *
 * The NodeModel interface combines various functionalities to describe a node within a
 * graph. It integrates unique identification, comments, attributes, and node-specific
 * attribute group properties, adhering to the overall graph structure's contract.
 *
 * This interface extends the following:
 * - Model<GraphSTMT>: Allows the conversion of the node model into an abstract syntax tree (AST).
 * - HasID: Ensures the node has a unique identifier.
 * - HasComment: Enables the node to optionally include a comment.
 * - HasAttributes: Provides a way to manage and modify a collection of attributes associated with the node.
 * - NodeAttributeGroup: Defines visual and structural attributes specific to nodes.
 *
 */
interface NodeModel : Model<GraphSTMT>, HasID, HasComment, HasAttributes, NodeAttributeGroup {
    /**
     * Converts the current node model into a forward reference node representation.
     *
     * This method creates a `ForwardRefNode` object using the node's unique identifier and
     * optionally adds a port or compass direction for further specificity.
     *
     * @param port An optional string representing the port of the node. This can be used to reference
     *             specific subcomponents or subdivisions of the node.
     * @param compass An optional `Compass` representing a specific direction*/
    fun toRef(port: String? = null, compass: Compass? = null): ForwardRefNode = ForwardRefNode(id, port, compass)

    override fun toAST() = GraphSTMTBuilder {
        comment?.let {
            comment(it)
        }
        node(id.quoted()) {
            for (attr in attributes) {
                load(attr.toAST())
            }
        }
    }.stmts
}
