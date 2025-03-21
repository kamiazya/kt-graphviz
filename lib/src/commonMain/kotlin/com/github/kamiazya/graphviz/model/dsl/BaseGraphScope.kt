package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.BaseGraphModel
import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.SubgraphModel

/**
 * Represents the base scope for constructing a graph. This interface provides methods for creating nodes, edges,
 * subgraphs, root graphs, forward references, and attribute groups, as well as managing these elements within a graph
 * structure.
 *
 * @param T The type of graph model that this scope operates on, constrained to `BaseGraphModel`.
 */
public interface BaseGraphScope<T : BaseGraphModel> :
    CreateNode,
    CreateEdge,
    CreateRootGraph,
    CreateSubgraph,
    CreateForwardRef,
    CreateEdgeTargetCluster,
    ApplyNodeAttributeGroup,
    ApplyEdgeAttributeGroup,
    ApplyGroupAttributeGroup,
    BaseGraphModel {

    /**
     * Creates a node within the graph and adds it to the structure.
     *
     * @param id The unique identifier of the node.
     * @param comment An optional comment associated with the node.
     * @param block A block providing the scope for further configuring the node.
     * @return The created and added node model.
     */
    override fun node(id: String, comment: String?, block: NodeScope.() -> Unit): NodeModel =
        super<CreateNode>.node(id, comment, block).also { addNode(it) }

    /**
     * Creates and adds an edge to the graph with specified edge distributions and optional configurations.
     *
     * @param first The first edge distribution.
     * @param second The second edge distribution.
     * @param others Additional edge distributions.
     * @param comment An optional comment associated with the edge.
     * @param block A lambda with a receiver of type EdgeScope to further configure the edge.
     * @return The created and added edge model.
     */
    override fun edge(
        first: EdgeDistribution,
        second: EdgeDistribution,
        vararg others: EdgeDistribution,
        comment: String?,
        block: EdgeScope.() -> Unit
    ): EdgeModel = super<CreateEdge>.edge(
        first,
        second,
        *others,
        comment = comment,
        block = block
    ).also { addEdge(it) }

    /**
     * Creates a subgraph within the graph and adds it to the structure.
     *
     * @param id The unique identifier of the subgraph, or `null` if omitted.
     * @param comment An optional comment associated with the subgraph.
     * @param block A block providing the scope for further configuring the subgraph.
     * @return The created and added subgraph model.
     */
    override fun subgraph(id: String?, comment: String?, block: SubgraphScope.() -> Unit): SubgraphModel =
        super<CreateSubgraph>.subgraph(id, comment, block).also { addSubgraph(it) }
}
