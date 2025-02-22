package com.github.kamiazya.graphviz

@DslMarker
annotation class DotDslMarker

@DotDslMarker
public interface CreateNodeDsl : HasContext {
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
        block: NodeModel.() -> Unit = {
        }
    ): NodeModel = context.createNode(id = id, comment = comment).apply(block)
}

@DotDslMarker
public interface ApplyNodeAttibuteGroupDsl : HasNodeAttributeGroupModel {
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
    fun node(block: NodeAttributeGroupModel.() -> Unit) = nodeAttributes.block()
}

@DotDslMarker
public interface CreateEdgeDsl : HasContext {
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
        block: EdgeModel.() -> Unit = {}
    ): EdgeModel = context.createEdge(listOf(first, second, *others), comment = comment).apply(block)

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
    operator fun EdgeModel.invoke(block: EdgeModel.() -> Unit): EdgeModel = apply(block)

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
public interface ApplyEdgeAttibuteGroupDsl : HasEdgeAttributeGroupModel {
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
    fun edge(block: EdgeAttributeGroupModel.() -> Unit) {
        edgeAttributes.block()
    }
}

@DotDslMarker
public interface CreateRefDsl {
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
public interface CreateRefsDsl {
    /**
     * Create a cluster of node references.
     */
    fun refs(vararg refs: NodeRef): ClusterEdgeTarget = EdgeTargetList(*refs)

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
    infix fun String.and(other: NodeRef): ClusterEdgeTarget = EdgeTargetList(ForwardRefNode(this), other)

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
    infix fun NodeRef.and(other: String): ClusterEdgeTarget = EdgeTargetList(this, ForwardRefNode(other))

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
    infix fun NodeRef.and(other: NodeRef): ClusterEdgeTarget = EdgeTargetList(this, other)

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
    infix fun ClusterEdgeTarget.and(other: NodeRef): ClusterEdgeTarget = this and other

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
    infix fun ClusterEdgeTarget.and(other: String): ClusterEdgeTarget = this and ForwardRefNode(other)
}

@DotDslMarker
interface GroupGroupAttributeDsl : HasGraphAttributes {

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
    fun graph(block: GraphAttributeGroupModel.() -> Unit) = graphAttributes.block()
}

@Suppress("ClassNaming")
public object strict

@DotDslMarker
public interface CreateRootGraphDsl : HasContext {

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
        block: RootGraphModel.() -> Unit
    ): RootGraphModel = context.createGraph(strict = false, id = id, comment = comment).apply(block)

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
        block: RootGraphModel.() -> Unit
    ): RootGraphModel = context.createDigraph(strict = false, id = id, comment = comment).apply(block)

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
    infix fun strict.digraph(block: RootGraphModel.() -> Unit): RootGraphModel =
        context.createDigraph(strict = true).apply(block)

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
        block: RootGraphModel.() -> Unit,
    ): RootGraphModel =
        context.createDigraph(strict = true, id = id, comment = comment).apply(block)

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
    infix fun strict.graph(block: RootGraphModel.() -> Unit): RootGraphModel =
        context.createGraph(strict = true).apply(block)

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
        block: RootGraphModel.() -> Unit,
    ): RootGraphModel =
        context.createGraph(strict = true, id = id, comment = comment).apply(block)
}

@DotDslMarker
public interface CreateSubgraphDsl : HasContext {
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
        block: SubgraphModel.() -> Unit,
    ): SubgraphModel =
        context.createSubgraph(id = id, comment = comment).apply(block)
}

/**
 * A context of the DOT DSL.
 *
 * This interface is a collection of DSLs to create a DOT.
 */
public interface ContextDsl :
    CreateNodeDsl,
    CreateEdgeDsl,
    CreateRefDsl,
    CreateRefsDsl,
    CreateRootGraphDsl,
    CreateSubgraphDsl

/**
 * Enter the DOT DSL.
 *
 * @param comment A comment of the DOT.
 * @param context A context of the DOT.
 * @param block A block to create a DOT.
 * @return A DOT.
 * @see DotModel
 */
fun dot(
    comment: String? = null,
    context: ModelContext = ModelContext.default,
    block: DotModel.() -> Unit
): DotModel = context.createDot(comment).apply(block)
