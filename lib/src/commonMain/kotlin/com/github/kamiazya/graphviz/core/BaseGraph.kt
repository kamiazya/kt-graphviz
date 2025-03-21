package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.BaseGraphModel
import com.github.kamiazya.graphviz.model.EdgeAttributeGroupModel
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.GraphAttributeGroupModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.NodeAttributeGroupModel
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.SubgraphModel

/**
 * BaseGraph is an abstract class representing the foundational structure of a graph model.
 *
 * This class functions as the base for various graph types, supporting nodes, edges, subgraphs,
 * and attribute groups for graphs, nodes, and edges. It provides default property implementations
 * for managing graph components and attributes.
 *
 * @property context The context of the model, used for creating and managing graph-related objects.
 * @property id The unique identifier for the graph, which can be nullable.
 * @property comment An optional comment associated with the graph.
 * @property nodes A list of nodes contained within the graph.
 * @property edges A list of edges contained within the graph.
 * @property subgraphs A list of subgraphs contained within the graph.
 * @property graphAttributes Attributes specifically associated with the graph.
 * @property nodeAttributes Attributes to be applied to nodes within the graph.
 * @property edgeAttributes Attributes to be applied to edges within the graph.
 */
abstract class BaseGraph(
    override val context: ModelContext,
    override var id: String?,
    override var comment: String? = null,
) : BaseGraphModel, GraphAttributeGroup() {
    override var nodes: List<NodeModel> = emptyList()
    override var edges: List<EdgeModel> = emptyList()
    override var subgraphs: List<SubgraphModel> = emptyList()
    override val graphAttributes: GraphAttributeGroupModel = object : GraphAttributeGroupModel, GraphAttributeGroup() {}
    override val nodeAttributes: NodeAttributeGroupModel = object : NodeAttributeGroupModel, NodeAttributeGroup() {}
    override val edgeAttributes: EdgeAttributeGroupModel = object : EdgeAttributeGroupModel, EdgeAttributeGroup() {}
}
