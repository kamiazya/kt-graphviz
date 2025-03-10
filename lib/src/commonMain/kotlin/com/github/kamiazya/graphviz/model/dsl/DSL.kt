package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.BaseGraphModel
import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.EdgeAttributeGroupModel
import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.EdgeTargetCluster
import com.github.kamiazya.graphviz.model.EdgeTargetList
import com.github.kamiazya.graphviz.model.ForwardRefNode
import com.github.kamiazya.graphviz.model.GraphAttributeGroupModel
import com.github.kamiazya.graphviz.model.HasContext
import com.github.kamiazya.graphviz.model.HasEdgeAttributeGroupModel
import com.github.kamiazya.graphviz.model.HasGraphAttributes
import com.github.kamiazya.graphviz.model.HasNodeAttributeGroupModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.NodeAttributeGroupModel
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.NodeRef
import com.github.kamiazya.graphviz.model.RootGraphModel
import com.github.kamiazya.graphviz.model.SubgraphModel
import com.github.kamiazya.graphviz.type.Compass

@DslMarker
annotation class DotDslMarker

@DotDslMarker
public interface CreateNode : HasContext {
    /**
     * Create a node.
     *
     * @param id An ID of the node.
     * @param comment A comment of the node.
     * @param block A block to create a node.
     *
     * ## Example
     *
     * If you want to create a node with an ID "a" and set a color of the node to "red", you can use the following code.
     *
     * ```kotlin
     * node("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a [
     *  color="red";
     * ];
     * ```
     */
    fun node(
        id: String,
        comment: String? = null,
        block: NodeScope.() -> Unit = {}
    ): NodeModel = NodeScope(context.createNode(id = id, comment = comment)).apply(block)
}

@DotDslMarker
public interface ApplyNodeAttibuteGroup : HasNodeAttributeGroupModel {
    /**
     * Apply a block to node attributes.
     *
     * ## Example
     *
     * If you want to set a color of a node in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *   node {
     *     color = "red"
     *   }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *  node [
     *    color="red";
     *  ];
     * };
     * ```
     */
    fun node(block: NodeAttributeGroupScope.() -> Unit) = NodeAttributeGroupScope(nodeAttributes).block()
}

@DotDslMarker
public interface CreateEdge : HasContext {
    /**
     * Create an edge.
     * @param distributions A list of edge distributions.
     * @param comment A comment of the edge.
     * @param block A block to create an edge.
     * @return An edge.
     *
     * ## Example
     *
     * If you want to create an edge from a node "a" to a node "b" and set a color of the edge to "red",
     * you can use the following code.
     *
     * ```kotlin
     * edge("a", "b") {
     *   color = "red"
     * }
     * ```
     *
     * And you can also create an edge from a node "a" to a node "b" and a node "c"
     * and set a color of the edge to "red".
     *
     * ```kotlin
     * edge("a", "b", "c") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> b [
     *   color="red";
     * ];
     *
     * a -> b -> c [
     *   color="red";
     * ];
     *
     *
     * If you already have a created node, you can use the following code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     *
     * edge(a, b) {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> b [
     *  color="red";
     * ];
     * ```
     */
    fun edge(
        first: EdgeDistribution,
        second: EdgeDistribution,
        vararg others: EdgeDistribution,
        comment: String? = null,
        block: EdgeScope.() -> Unit = {}
    ): EdgeModel = EdgeScope(context.createEdge(listOf(first, second, *others), comment = comment)).apply(block)

    /**
     * Apply a block to edge.
     *
     * ## Example
     *
     * If you want to set a color of an edge in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *   ("a" - "b") {
     *    color = "red"
     *  }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *   a -- b [
     *     color="red";
     *   ];
     * };
     * ```
     */
    operator fun EdgeModel.invoke(block: EdgeScope.() -> Unit): EdgeModel = EdgeScope(this).apply(block)

    /**
     * Append an edge distribution to an edge.
     *
     * ## Example
     *
     * If you want to add an edge distribution to an edge,
     * you can use the following code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     * val c = node("c")
     *
     * val e1 = edge(a, b) - c
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a;
     * b;
     * c;
     *
     * a -> b -> c;
     * ```
     */
    operator fun EdgeModel.minus(other: EdgeDistribution): EdgeModel = apply {
        addDistribution(other)
    }

    /**
     * Append an edge distribution to an edge.
     *
     * ## Example
     *
     * If you want to add an edge distribution to an edge,
     * you can use the following code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     *
     * val e1 = edge(a, b) - "c"
     * ```
     *
     *
     * This above code's "c" is a forward reference of node.
     * So, the above code is equivalent to the following kotlin code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     *
     * val e1 = edge(a, b) - ref("c")
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a;
     * b;
     *
     * a -> b -> c;
     * ```
     */
    operator fun EdgeModel.minus(other: String): EdgeModel = apply {
        addDistribution(ForwardRefNode.from(other))
    }

    /**
     * Create an edge with a forward reference of a node.
     *
     * ## Example
     *
     * If you want to create an edge with a forward reference of a node,
     *
     * ```kotlin
     * val b = node("b") {
     *  color = "red"
     * }
     * "a" - b
     * ```
     *
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b [
     *   color="red";
     * ];
     *
     * a -> b;
     * ```
     */
    operator fun String.minus(other: EdgeDistribution): EdgeModel {
        return edge(ForwardRefNode.from(this), other)
    }

    /**
     * Create an edge with a forward reference of a node.
     *
     * ## Example
     *
     * If you want to create an edge with a forward reference of a node,
     *
     * ```kotlin
     * val a = node("a") {
     *   color = "red"
     * }
     * val b = node("b")
     *
     * a - b
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a [
     *   color="red";
     * ];
     * b;
     *
     * a -> b;
     * ```
     */
    operator fun EdgeDistribution.minus(other: EdgeDistribution): EdgeModel {
        return edge(this, other)
    }
}

@DotDslMarker
public interface ApplyEdgeAttibuteGroup : HasEdgeAttributeGroupModel {
    /**
     * Apply a block to edge attributes.
     *
     * ## Example
     *
     * If you want to set a color of an edge in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *    edge {
     *      color = "red"
     *    }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *   edge [
     *     color="red";
     *   ];
     * };
     * ```
     */
    fun edge(block: EdgeAttributeGroupScope.() -> Unit) = EdgeAttributeGroupScope(edgeAttributes).block()
}

@DotDslMarker
public interface CreateForwardRef {
    /**
     * Create a forward reference of a node by String.
     *
     * ## Example
     *
     * ### ID
     *
     * If you want to create a forward reference of a node with an ID "a",
     *
     * ```kotlin
     * ref("a")
     * ```
     *
     * ### ID and Port
     *
     * If you want to create a forward reference of a node with an ID "a" and a port "port_of_a",
     *
     * ```kotlin
     * ref("a:port_of_a")
     * ```
     *
     * ### ID, Port, and Compass
     *
     * If you want to create a forward reference of a node with an ID "a" and a port "port_of_a" and a compass "N",
     *
     * ```kotlin
     * ref("a:port_of_a:N")
     * ```
     */
    fun ref(ref: String): ForwardRefNode = ForwardRefNode.from(ref)

    /**
     * Create a forward reference of a node.
     *
     * ## Example
     *
     * If you want to create a forward reference of a node with an ID "a",
     *
     * ```kotlin
     * ref("a")
     * ```
     */
    fun ref(id: String, port: String? = null, compass: Compass? = null): ForwardRefNode = ForwardRefNode(
        id,
        port,
        compass
    )
}

@DotDslMarker
public interface CreateEdgeTargetCluster {
    /**
     * Create a cluster of node references.
     */
    fun clusterOf(vararg refs: NodeRef): EdgeTargetCluster = EdgeTargetList(*refs)

    /**
     * Create a cluster of node references.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c".
     *
     * ```kotlin
     * ("a" - ("b" and "c"))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> { b, c };
     * ```
     */
    infix fun String.and(other: NodeRef): EdgeTargetCluster = EdgeTargetList(ForwardRefNode.from(this), other)

    /**
     * Create a cluster of node references.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c".
     *
     * ```kotlin
     * val b = ref("b")
     *
     * ("a" - (b and "c"))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> { b, c };
     * ```
     */
    infix fun NodeRef.and(other: String): EdgeTargetCluster = EdgeTargetList(this, ForwardRefNode.from(other))

    /**
     * Create a cluster of node references.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c".
     *
     * ```kotlin
     * val b = node("b")
     * val c = node("c")
     *
     * ("a" - (b and c))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b;
     * c;
     *
     * a -> { b, c };
     * ```
     */
    infix fun NodeRef.and(other: NodeRef): EdgeTargetCluster = EdgeTargetList(this, other)

    /**
     * Create a cluster of node references.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c" and "d".
     *
     * ```kotlin
     * val b = node("b")
     * val c = node("c")
     * val d = node("d")
     *
     * ("a" - (b and c and d))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b;
     * c;
     *
     * a -> { b, c, d };
     * ```
     */
    infix fun EdgeTargetCluster.and(other: NodeRef): EdgeTargetCluster = this and other

    /**
     * Create a cluster of node references.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c" and "d".
     *
     * ```kotlin
     * val b = node("b")
     * val c = node("c")
     *
     * ("a" - (b and c and "d"))
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b;
     * c;
     *
     * a -> { b, c, d };
     * ```
     */
    infix fun EdgeTargetCluster.and(other: String): EdgeTargetCluster = this and ForwardRefNode.from(other)
}

@DotDslMarker
interface ApplyGroupAttibuteGroup : HasGraphAttributes {

    /**
     * A group of graph attributes.
     *
     * ## Example
     *
     * If you want to set a color of a graph in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *   graph {
     *     color = "red"
     *   }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *  graph [
     *    color="red";
     *  ];
     * };
     */
    fun graph(block: GraphAttributeGroupScope.() -> Unit) = GraphAttributeGroupScope(graphAttributes).block()
}

@Suppress("ClassNaming")
public object strict

@DotDslMarker
public interface CreateRootGraph : HasContext {

    /**
     * Create a graph.
     *
     * @param id An ID of the graph.
     * @param comment A comment of the graph.
     * @param strict A flag to set the graph to strict.
     * @param block A block to create a graph.
     * @return A graph.
     *
     * ## Example
     *
     * If you want to create a graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * graph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph a {
     *   color="red";
     * };
     * ```
     */
    fun graph(
        id: String? = null,
        comment: String? = null,
        strict: Boolean? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel = RootGraphScope(context.createGraph(strict = false, id = id, comment = comment)).apply(block)

    /**
     * Create a digraph.
     *
     * @param id An ID of the digraph.
     * @param comment A comment of the digraph.
     * @param strict A flag to set the digraph to strict.
     * @param block A block to create a digraph.
     *
     * ## Example
     *
     * If you want to create a digraph with an ID "a" and set a color of the digraph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * digraph("a") {
     *   color = "red"
     * }
     *
     */
    fun digraph(
        id: String? = null,
        comment: String? = null,
        strict: Boolean? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel = RootGraphScope(context.createDigraph(strict = false, id = id, comment = comment)).apply(block)

    /**
     * Create a strict graph.
     * @param block A block to create a strict graph.
     * @return A strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict digraph {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict digraph {
     *  color="red";
     * };
     * ```
     */
    infix fun strict.digraph(block: RootGraphScope.() -> Unit): RootGraphModel =
        RootGraphScope(context.createDigraph(strict = true)).apply(block)

    /**
     * Create a strict graph.
     * @param id An ID of the strict graph.
     * @param comment A comment of the strict graph.
     * @param block A block to create a strict graph.
     * @return A strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.digraph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict digraph a {
     *   color="red";
     * };
     * ```
     */
    fun strict.digraph(
        id: String,
        comment: String? = null,
        block: RootGraphScope.() -> Unit,
    ): RootGraphModel =
        RootGraphScope(context.createDigraph(strict = true, id = id, comment = comment)).apply(block)

    /**
     * Create a strict graph.
     * @param block A block to create a strict graph.
     * @return A strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     *
     * ```kotlin
     * strict graph {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict graph {
     *   color="red";
     * };
     * ```
     */
    infix fun strict.graph(block: RootGraphScope.() -> Unit): RootGraphModel =
        RootGraphScope(context.createGraph(strict = true)).apply(block)

    /**
     * Create a strict graph.
     *
     * @param id An ID of the strict graph.
     * @param block A block to create a strict graph.
     * @return A strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     *
     * ```kotlin
     * strict.graph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict graph a {
     *   color="red";
     * };
     * ```
     */
    fun strict.graph(
        id: String,
        comment: String? = null,
        block: RootGraphScope.() -> Unit,
    ): RootGraphModel =
        RootGraphScope(context.createGraph(strict = true, id = id, comment = comment)).apply(block)
}

@DotDslMarker
public interface CreateSubgraph : HasContext {
    /**
     * Create a subgraph.
     *
     * @param id An ID of the subgraph.
     * @param comment A comment of the subgraph.
     * @param block A block to create a subgraph.
     * @return A subgraph.
     *
     * ## Example
     *
     * If you want to create a subgraph with an ID "a" and set a color of the subgraph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * subgraph("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * subgraph a {
     *   color="red";
     * };
     * ```
     */
    fun subgraph(
        id: String? = null,
        comment: String? = null,
        block: SubgraphScope.() -> Unit,
    ): SubgraphModel =
        SubgraphScope(context.createSubgraph(id = id, comment = comment)).apply(block)
}

public interface BaseGraphScope<T : BaseGraphModel> :
    CreateNode,
    CreateEdge,
    CreateRootGraph,
    CreateSubgraph,
    CreateForwardRef,
    CreateEdgeTargetCluster,
    ApplyNodeAttibuteGroup,
    ApplyEdgeAttibuteGroup,
    ApplyGroupAttibuteGroup,
    BaseGraphModel {

    /**
     * Create a node and add it to the graph.
     * @param id An ID of the node.
     * @param comment A comment of the node.
     * @param block A block to create a node.
     * @return A node.
     */
    override fun node(id: String, comment: String?, block: NodeScope.() -> Unit): NodeModel =
        super<CreateNode>.node(id, comment, block).also { addNode(it) }

    /**
     * Create an edge and add it to the graph.
     * @param first An edge distribution.
     * @param second An edge distribution.
     * @param others A list of edge distributions.
     * @param comment A comment of the edge.
     * @param block A block to create an edge.
     * @return An edge.
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
     * Create a subgraph and add it to the graph.
     *
     * @param id An ID of the subgraph.
     * @param comment A comment of the subgraph.
     * @param block A block to create a subgraph.
     * @return A subgraph.
     */
    override fun subgraph(id: String?, comment: String?, block: SubgraphScope.() -> Unit): SubgraphModel =
        super<CreateSubgraph>.subgraph(id, comment, block).also { addSubgraph(it) }
}

public class NodeScope(
    private val node: NodeModel
) : NodeModel by node

public class EdgeScope(
    private val edge: EdgeModel
) : EdgeModel by edge

public class NodeAttributeGroupScope(
    private val nodeAttributeGroup: NodeAttributeGroupModel
) : NodeAttributeGroupModel by nodeAttributeGroup

public class EdgeAttributeGroupScope(
    private val edgeAttributeGroup: EdgeAttributeGroupModel
) : EdgeAttributeGroupModel by edgeAttributeGroup

public class GraphAttributeGroupScope(
    private val graphAttributeGroup: GraphAttributeGroupModel
) : GraphAttributeGroupModel by graphAttributeGroup

public class RootGraphScope(
    private val rootGraph: RootGraphModel
) :
    BaseGraphScope<RootGraphModel>,
    RootGraphModel by rootGraph

public class SubgraphScope(
    private val subgraph: SubgraphModel
) :
    BaseGraphScope<SubgraphModel>,
    SubgraphModel by subgraph

public class DotScope(val dot: DotModel) :
    DotModel by dot,
    CreateRootGraph,
    CreateNode,
    CreateEdge,
    CreateSubgraph,
    CreateForwardRef {
    /**
     * Create a graph and set it as a root graph.
     *
     * @param id An ID of the graph.
     * @param comment A comment of the graph.
     * @param block A block to create a graph.
     * @return A graph.
     *
     * ## Example
     *
     * If you want to create a graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * graph("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph a {
     *  color="red";
     * };
     * ```
     */
    fun graph(
        id: String? = null,
        comment: String? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.graph(id, comment, strict = false, block).also { setRootGraph(it) }

    /**
     * Create a digraph and set it as a root graph.
     *
     * @param id An ID of the digraph.
     * @param comment A comment of the digraph.
     * @param block A block to create a digraph.
     * @return A digraph.
     *
     * ## Example
     *
     * If you want to create a digraph with an ID "a" and set a color of the digraph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * digraph("a") {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * digraph a {
     * color="red";
     * };
     * ```
     */
    fun digraph(
        id: String? = null,
        comment: String? = null,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.digraph(
            id,
            comment,
            strict = false,
            block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Create a strict graph and set it as a root graph.
     *
     * @param block A block to create a strict graph.
     * @return A strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.graph("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict graph a {
     *   color="red";
     * };
     * ```
     */
    override infix fun strict.digraph(block: RootGraphScope.() -> Unit): RootGraphModel =
        super<CreateRootGraph>.digraph(
            id = null,
            comment = null,
            strict = true,
            block = block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Create a strict digraph and set it as a root graph.
     *
     * @param id An ID of the strict digraph.
     * @param comment A comment of the strict digraph.
     * @param block A block to create a strict digraph.
     * @return A strict digraph.
     *
     * ## Example
     *
     * If you want to create a strict digraph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.digraph("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict digraph a {
     *   color="red";
     * };
     * ```
     */
    override fun strict.digraph(
        id: String,
        comment: String?,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.digraph(
            id,
            comment,
            strict = true,
            block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Create a strict graph and set it as a root graph.
     *
     * @param block A block to create a strict graph.
     * @return A strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.graph {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict graph {
     *   color="red";
     * };
     * ```
     */
    override infix fun strict.graph(block: RootGraphScope.() -> Unit): RootGraphModel =
        super<CreateRootGraph>.graph(
            id = null,
            comment = null,
            strict = true,
            block = block,
        ).also {
            setRootGraph(it)
        }

    /**
     * Create a strict graph and set it as a root graph.
     *
     * @param id An ID of the strict graph.
     * @param comment A comment of the strict graph.
     * @param block A block to create a strict graph.
     * @return A strict graph.
     *
     * ## Example
     *
     * If you want to create a strict graph with an ID "a" and set a color of the graph to "red",
     * you can use the following code.
     *
     * ```kotlin
     * strict.graph("a") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * strict graph a {
     *   color="red";
     * };
     * ```
     */
    override fun strict.graph(
        id: String,
        comment: String?,
        block: RootGraphScope.() -> Unit
    ): RootGraphModel =
        super<CreateRootGraph>.graph(
            id,
            comment,
            strict = true,
            block,
        ).also {
            setRootGraph(it)
        }
}

/**
 * Enter the DOT DSL.
 *
 * @param comment A comment of the DOT.
 * @param context A context of the DOT.
 * @param block A block to create a DOT.
 * @return A DOT.
 * @see DotModel
 */
fun ModelContext.dot(
    comment: String? = null,
    block: DotScope.() -> Unit
): DotModel = DotScope(
    createDot(
        context = this,
        comment = comment,
    )
).apply(block)
