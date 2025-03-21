package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.SubgraphModel

/**
 * Represents a subgraph within a larger graph structure.
 *
 * A Subgraph is a specialized type of graph model that serves as a component within
 * a parent graph, enabling hierarchical organization of nodes, edges, and attributes.
 *
 * @property context The context in which this subgraph resides, used for creating and managing graph-related objects.
 * @property id An optional identifier for the subgraph.
 * @property comment An optional comment associated with the subgraph for documentation or descriptive purposes.
 * @property attributes A list of attributes specific to this subgraph.
 * @property nodes A list of node models contained within this subgraph.
 * @property edges A list of edge models representing connections between nodes within this subgraph.
 * @property subgraphs A list of nested subgraphs contained within this subgraph.
 *
 * Inherits properties and functionality from the BaseGraph class and implements the SubgraphModel interface.
 */
class Subgraph(
    context: ModelContext,
    override var id: String? = null,
    override var comment: String? = null,
) : BaseGraph(
    context = context,
    id = id,
    comment = comment,
),
    SubgraphModel {
    override var attributes: List<Attribute> = emptyList()
    override var nodes: List<NodeModel> = emptyList()
    override var edges: List<EdgeModel> = emptyList()
    override var subgraphs: List<SubgraphModel> = emptyList()
}
