package com.github.kamiazya.graphviz

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

public typealias AttributeValue = Any

/**
 * Attribute is a pair of key and value.
 */
public open class Attribute(
    public var key: String,
    public var value: AttributeValue
) {
    operator fun component1() = key
    operator fun component2() = value
}

/**
 * EdgeDistribution is a distribution of edges.
 * It can be a edge target like a node or ID of a node, or a list of edge targets.
 */
sealed interface EdgeDistribution

/**
 * NodeRef is a target of an edge.
 * It can be a node or ID of a node.
 */
public sealed interface NodeRef : EdgeDistribution

public class ForwardRefNode(
    val id: String,
    val port: String? = null,
    val compass: Compass? = null,
) : NodeRef

/**
 * EdgeTargetList is a collection of edge targets.
 */
interface ClusterEdgeTarget : Collection<NodeRef>, EdgeDistribution

/**
 * HasComment is an interface for models that have a comment.
 */
public interface HasComment {
    /**
     * A comment for the model.
     */
    var comment: String?
}

/**
 * HasID is an interface for models that have an ID.
 */
public interface HasID {
    var id: String
}

/**
 * HasNullableID is an interface for models that have a nullable ID.
 */
public interface HasNullableID {
    var id: String?
}

/**
 * HasAttributes is an interface for models that have attributes.
 */
public interface HasAttributes {
    /**
     * A list of attributes.
     */
    var attributes: List<Attribute>

    /**
     * Get an attribute value by key.
     * @param key A key of the attribute.
     * @return An attribute value.
     */
    fun <T : AttributeValue> getAttribute(key: String): T? = attributes.find { it.key == key }?.value?.let {
        @Suppress("UNCHECKED_CAST")
        return it as T
    }

    /**
     * Set an attribute value by key.
     * @param key A key of the attribute.
     * @param value A value of the attribute.
     */
    fun <T : AttributeValue> setAttribute(key: String, value: T) {
        val attribute = attributes.find { it.key == key }
        if (attribute != null) {
            attribute.value = value
        } else {
            attributes += Attribute(key, value)
        }
    }

    /**
     * Remove an attribute by key.
     * @param key A key of the attribute.
     */
    fun removeAttribute(key: String) {
        attributes.find { it.key == key }?.let {
            attributes -= it
        }
    }

    /**
     * Check if an attribute exists by key.
     * @param key A key of the attribute.
     * @return true if the attribute exists.
     *        false if the attribute does not exist.
     */
    fun exists(key: String): Boolean = attributes.any { it.key == key }

    /**
     * Clear all attributes.
     */
    fun clear() {
        attributes = emptyList()
    }
}

public interface AttributeGroupModel : HasAttributes

/**
 * AttributeValueOf is a delegate for an attribute value.
 *
 * @param T A type of the model.
 * @param V A type of the attribute value.
 * @param actualName An actual name of the attribute.
 * @param defaultValue A default value of the attribute.
 * @return A delegate for an attribute value.
 *
 * @example simple case
 * ```
 * class TestModel : AttributeGroupModel {
 *    var color: String? by AttributeValueOf()
 * }
 * ```
 *
 * @example with actual name
 * ```
 * class TestModel : AttributeGroupModel {
 *   var color: String? by AttributeValueOf("actual_color")
 * }
 *
 * val model = TestModel()
 * model.color = "red"
 * model.getAttribute("actual_color") // "red"
 * model.color // "red"
 * ```
 *
 * @example with default value
 * ```
 * class TestModel : AttributeGroupModel {
 *  var color: String? by AttributeValueOf(defaultValue = "red")
 * }
 *
 * val model = TestModel()
 * model.color // "red"
 * ```
 */
public class AttributeValueOf<T, V>(
    /**
     * An actual name of the attribute.
     */
    private val actualName: String? = null,

    /**
     * A default value of the attribute.
     */
    private val defaultValue: V? = null,
    /**
     * A modifier of the attribute.
     */
    private val modifier: ((V) -> V)? = null,
) : ReadWriteProperty<T, V?>
    where T : AttributeGroupModel {

    /**
     * When property is delegated, set the default value.
     */
    operator fun provideDelegate(thisRef: T, property: KProperty<*>): AttributeValueOf<T, V> {
        setValue(thisRef, property, defaultValue)
        return this
    }

    /**
     * When property is accessed, get the attribute value.
     */
    override operator fun getValue(thisRef: T, property: KProperty<*>): V? {
        return thisRef.getAttribute(actualName ?: property.name)
    }

    /**
     * When property is set, set the attribute value.
     * If the value is null, remove the attribute.
     * If the modifier is set, apply the modifier to the value before setting value.
     *
     */
    override operator fun setValue(thisRef: T, property: KProperty<*>, value: V?) {
        if (value == null) {
            thisRef.removeAttribute(actualName ?: property.name)
        } else {
            thisRef.setAttribute(actualName ?: property.name, modifier?.invoke(value) ?: value)
        }
    }
}

/**
 * AttributeGroupModel is an interface for attribute group models.
 */
public interface NodeAttributeGroupModel : AttributeGroupModel {
    var shape: String?
    var color: String?
}

/**
 * EdgeAttributeGroupModel is an interface for edge attribute group models.
 */
public interface EdgeAttributeGroupModel : AttributeGroupModel {
    var color: String?
}

/**
 * GraphAttributeGroupModel is an interface for graph attribute group models.
 */
public interface GraphAttributeGroupModel : AttributeGroupModel {
    var color: String?
}

/**
 * BaseAttributeGroup is a base class for attribute group models.
 */
public abstract class BaseAttributeGroup : AttributeGroupModel {
    override var attributes: List<Attribute> = emptyList()
}

/**
 * NodeAttributeGroupModel is a class for node attribute group models.
 */
public interface HasGraphAttributes {
    /**
     * A group of graph attributes.
     */
    val graphAttributes: GraphAttributeGroupModel
}

public interface HasNodeAttributeGroupModel {
    /**
     * A group of node attributes.
     */
    val nodeAttributes: NodeAttributeGroupModel
}

public interface HasEdgeAttributeGroupModel {
    /**
     * A group of edge attributes.
     */
    val edgeAttributes: EdgeAttributeGroupModel
}

/**
 * NodeAttributeGroup is a class for node attribute group models.
 */
public interface HasAttributeGroups :
    HasGraphAttributes,
    HasNodeAttributeGroupModel,
    HasEdgeAttributeGroupModel,
    GroupGroupAttributeDsl

/**
 * HasRootGraph is an interface for models that have a root graph.
 */
public interface HasRootGraph {
    /**
     * A root graph.
     */
    var graph: RootGraphModel?

    /**
     * Get the root graph.
     * @return A root graph.
     */
    fun getRootGraph(): RootGraphModel? = graph

    /**
     * Set the root graph.
     * @param root A root graph to set.
     */
    fun setRootGraph(root: RootGraphModel) {
        check(graph == null) { "Root graph is already set." }
        graph = root
    }
}

/**
 * NodeModel is an interface for node models.
 */
public interface NodeModel : HasID, HasComment, HasAttributes, NodeAttributeGroupModel, NodeRef {
    /**
     * Represent the port of the node.
     * @param port A port of the node.
     * @param compass A compass of the node.
     * @return A ForwardRefNode.
     */
    public fun port(port: String?, compass: Compass?): ForwardRefNode = ForwardRefNode(id, port, compass)
}

public interface HasEdgeDistributions {
    /**
     * A list of edge distributions.
     */
    var targets: List<EdgeDistribution>

    /**
     * Add an edge distribution.
     * @param distribution An edge distribution to add.
     */
    fun addDistribution(distribution: EdgeDistribution) {
        targets += distribution
    }

    /**
     * Remove an edge distribution.
     * @param distribution An edge distribution to remove.
     */
    fun removeDistribution(distribution: EdgeDistribution) {
        targets -= distribution
    }
}

/**
 * ModelContext is a context for creating models.
 */
public interface ModelContext {
    /**
     * Create a dot.
     * @param comment A comment of the dot.
     * @param block A block to create a dot.
     * @return A dot.
     */
    fun createDot(comment: String?): DotModel = Dot(comment)

    /**
     * Create a digraph.
     * @param strict A strict flag.
     * @param id An ID of the digraph.
     * @param block A block to create a digraph.
     * @return A digraph.
     */
    fun createDigraph(
        strict: Boolean = false,
        id: String?,
    ): RootGraphModel = Digraph(strict, id)

    /**
     * Create a graph.
     * @param strict A strict flag.
     * @param id An ID of the graph.
     * @param block A block to create a graph.
     * @return A graph.
     */
    fun createGraph(
        strict: Boolean = false,
        id: String?,
    ): RootGraphModel = Graph(strict, id)

    /**
     * Create a subgraph.
     * @param id An ID of the subgraph.
     * @param block A block to create a subgraph.
     * @return A subgraph.
     */
    fun createSubgraph(id: String?): SubgraphModel = Subgraph(id)

    /**
     * Create a node.
     * @param id An ID of the node.
     * @param block A block to create a node.
     * @return A node.
     */
    fun createNode(id: String): NodeModel = Node(id)

    /**
     * Create an edge.
     * @param targets A list of edge distributions.
     * @param block A block to create an edge.
     * @return An edge.
     */
    fun createEdge(targets: List<EdgeDistribution>): EdgeModel = Edge(targets)

    companion object {
        var default: ModelContext = object : ModelContext {}
    }
}

/**
 * HasContext is an interface for models that have a context.
 */
public interface HasContext {
    /**
     * A model context.
     */
    val context: ModelContext

    // fun <T : HasContext>with(context: ModelContext, @DotDslMarker T.() -> Unit): T
}

/**
 * HasNodes is an interface for models that have nodes.
 */
public interface HasNodes {
    /**
     * A list of nodes.
     */
    var nodes: List<NodeModel>

    /**
     * Get a node by ID.
     */
    fun getNode(id: String): NodeModel? = nodes.find { it.id == id }

    /**
     * Add a node.
     * @param node A node to add.
     */
    fun addNode(node: NodeModel) {
        nodes += node
    }

    /**
     * Remove a node.
     * @param node A node to remove.
     */
    fun removeNode(node: NodeModel) {
        nodes -= node
    }

    /**
     * Clear all nodes.
     */
    fun clearNodes() {
        nodes = emptyList()
    }
}

/**
 * HasEdges is an interface for models that have edges.
 */
public interface HasEdges {
    /**
     * A list of edges.
     */
    var edges: List<EdgeModel>

    /**
     * Add an edge.
     * @param edge An edge to add.
     */
    fun addEdge(edge: EdgeModel) {
        edges += edge
    }

    /**
     * Remove an edge.
     * @param edge An edge to remove.
     */
    fun removeEdge(edge: EdgeModel) {
        edges -= edge
    }

    /**
     * Clear all edges.
     */
    fun clearEdges() {
        edges = emptyList()
    }
}

/**
 * HasSubgraphs is an interface for models that have subgraphs.
 */
public interface HasSubgraphs {
    /**
     * A list of subgraphs.
     */
    var subgraphs: List<SubgraphModel>

    /**
     * Add a subgraph.
     */
    fun addSubgraph(subgraph: SubgraphModel) {
        subgraphs += subgraph
    }

    /**
     * Remove a subgraph.
     */
    fun removeSubgraph(subgraph: SubgraphModel) {
        subgraphs -= subgraph
    }

    /**
     * Find a subgraph by ID.
     * @param id An ID of the subgraph.
     * @return A subgraph if found.
     *        null if not found.
     */
    fun findSubgraph(id: String): SubgraphModel? = subgraphs.find { it.id == id }

    /**
     * Clear all subgraphs.
     */
    fun clearSubgraphs() {
        subgraphs = emptyList()
    }
}

/**
 * DotModel is an interface for dot models.
 */
public interface DotModel : HasComment, HasRootGraph, HasContext, RootGraphDsl

/**
 * EdgeModel is an interface for edge models.
 */
public interface EdgeModel : HasComment, HasAttributes, HasEdgeDistributions, EdgeAttributeGroupModel

/**
 * BaseGraphModel is an interface for base of graph models.
 */
public interface BaseGraphModel :
    HasNullableID,
    HasComment,
    HasAttributes,
    HasNodes,
    HasEdges,
    HasSubgraphs,
    HasContext,
    HasAttributeGroups,
    NodeDsl,
    CreateEdgeDsl,
    ApplyEdgeAttibuteGroupDsl,
    RefDsl,
    RefsDsl

/**
 * RootGraphModel is an interface for root graph models.
 */
public interface RootGraphModel : BaseGraphModel {

    /**
     * A strict flag.
     */
    var strict: Boolean

    /**
     * A directed flag.
     */
    val directed: Boolean
}

/**
 * SubgraphModel is an interface for subgraph models.
 */
public interface SubgraphModel : BaseGraphModel {
    fun isCluster(): Boolean = id?.startsWith("cluster_") ?: false
}
