package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.AST
import com.github.kamiazya.graphviz.ast.Attribute
import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.AttributesSTMT
import com.github.kamiazya.graphviz.ast.ClusterNodeRefs
import com.github.kamiazya.graphviz.ast.Comment
import com.github.kamiazya.graphviz.ast.Dot
import com.github.kamiazya.graphviz.ast.DotSTMT
import com.github.kamiazya.graphviz.ast.Edge
import com.github.kamiazya.graphviz.ast.EdgeDistribution
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.Literal
import com.github.kamiazya.graphviz.ast.Node
import com.github.kamiazya.graphviz.ast.NodeRef
import com.github.kamiazya.graphviz.ast.RootGraph
import com.github.kamiazya.graphviz.ast.Subgraph

/**
 * A marker annotation used to define DSL boundaries within AST-related builders.
 *
 * The `ASTDslMarker` is applied to DSL builders to prevent accidental access to
 * outer scopes when constructing structures in the Abstract Syntax Tree (AST).
 * This ensures clearer and more predictable DSL usage by enforcing context isolation.
 *
 * Classes annotated with `@ASTDslMarker` form a distinct DSL scope and are typically
 * used for constructing buildable components of DOT graph representations, such as
 * statements, nodes, edges, attributes, and subgraphs.
 */
@DslMarker
annotation class ASTDslMarker

/**
 * A generic abstract class designed to build and manage a list of statements within
 * the Abstract Syntax Tree (AST) structure.
 *
 * The `STMTBuilder` serves as a base class for constructing various types of statements
 * in a DOT graph representation. It allows for the accumulation and management of
 * statement-specific elements derived from the `AST` interface.
 *
 * @param T A type parameter constrained to subtypes of the `AST` interface.
 *          This ensures that only valid AST element types can be managed by the builder.
 */
abstract class STMTBuilder<T : AST> {
    /**
     * Represents a mutable list of statements managed within an Abstract Syntax Tree (AST) structure.
     *
     * This list is used to accumulate and store AST elements such as attributes, comments, nodes,
     * edges, subgraphs, or other structural components of a DOT graph.
     *
     * The type parameter `T` ensures that only elements conforming to the `AST` interface
     * can be added to the list, maintaining type safety and consistency within the AST structure.
     *
     * This property is commonly modified using helper functions in derived builders for appending
     * statements such as attributes, comments, or substructures.
     */
    var stmts: MutableList<T> = mutableListOf()

    /**
     * Adds all statements from the provided list to the existing statements managed by the builder.
     *
     * @param stmts A list of statements to be added. Each statement must conform to the type parameter `T`
     *              constrained by the `AST` interface.
     */
    fun load(stmts: List<T>) {
        this.stmts.addAll(stmts)
    }
}

/**
 * Provides utility methods and extensions for creating `Literal` instances.
 *
 * The `LiteralBuilder` interface defines functions to facilitate the creation of
 * `Literal` objects with different quoting styles. It includes methods for explicit
 * literal creation as well as extension functions to simplify the conversion of
 * strings into `Literal` instances with predefined quoting styles.
 */
interface LiteralBuilder {
    /**
     * Creates a `Literal` instance with the specified value and quoting style.
     *
     * This function constructs a `Literal` object using the provided `value` and `quoted` parameters.
     * The `Literal` represents a value in a DOT graph's AST, with quoting style behavior specified
     * by the `Literal.Quote` type.
     *
     * @param value The string value of the literal.
     * @param quoted The quoting style to apply to the literal. Defaults to `Literal.Quote.QUOTED`.
     */
    fun literalOf(
        value: String,
        quoted: Literal.Quote = Literal.Quote.QUOTED,
    ) = Literal(
        value = value,
        quoted = quoted,
    )

    /**
     * Converts the string to a `Literal` with a quoted style.
     *
     * This extension function creates a `Literal` object by wrapping the string
     * with a `Literal.Quote.QUOTED` style, indicating the string should be enclosed
     * in quotes within the DOT graph representation.
     *
     * @return A `Literal` instance with the value of the string and a `QUOTED` quoting style.
     */
    fun String.quoted(): Literal = literalOf(this, quoted = Literal.Quote.QUOTED)
    /**
     * Converts the string to a `Literal` with an unquoted style.
     *
     * This extension function creates a `Literal` object by wrapping the string
     * with a `Literal.Quote.UNQUOTED` style, indicating the string should not
     * be enclosed in quotes within the DOT graph representation.
     *
     * @return A `Literal` instance with the value of the string and an `UNQUOTED` quoting style.
     */
    fun String.unquoted(): Literal = literalOf(this, quoted = Literal.Quote.UNQUOTED)
    /**
     * Converts the string to a `Literal` with an HTML quoting style.
     *
     * This extension function creates a `Literal` object by wrapping the string with a
     * `Literal.Quote.HTML` style, indicating the string should be interpreted
     * and formatted as HTML content within the DOT graph representation.
     *
     * @return A `Literal` instance with the value of the string and an `HTML` quoting style.
     */
    fun String.html(): Literal = literalOf(this, quoted = Literal.Quote.HTML)
}

/**
 * A builder class for creating and managing attribute-related statements in a DOT graph's AST.
 *
 * The `AttributeSTMTBuilder` is a DSL-based utility that facilitates the construction of statements
 * categorized as `AttributesSTMT`. These statements include attributes and comments specific to graph
 * elements such as nodes, edges, or the overall graph itself. The builder utilizes the `STMTBuilder`
 * as its base class and implements the `LiteralBuilder` interface for handling literals.
 *
 * This class is marked with `@ASTDslMarker` to define a distinct scope in the DSL and prevent accidental
 * access to outer scopes.
 *
 * @constructor Initializes the builder with a lambda block defining its structure.
 * @param body A lambda function with the receiver type `AttributeSTMTBuilder` used to define and populate
 *             the builder's structure.
 */
@ASTDslMarker
class AttributeSTMTBuilder(body: AttributeSTMTBuilder.() -> Unit) : STMTBuilder<AttributesSTMT>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Appends a comment to the list of statements managed by the builder.
     *
     * @param value The textual content of the comment to be added.
     * @param kind The type of comment to use, specified by the `Comment.Kind` enum. Defaults to `Comment.Kind.SLASH`.
     */
    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    /**
     * Adds an attribute to the list of statements in the builder.
     *
     * @param key The key of the attribute being created, represented as a `Literal`.
     * @param value The value of the attribute being created, represented as a `Literal`.
     */
    fun attribute(
        key: Literal,
        value: Literal,
    ) {
        stmts += Attribute(
            key = key,
            value = value,
        )
    }
}

/**
 * A builder class for constructing edge distributions within a DOT graph's Abstract Syntax Tree (AST).
 *
 * `EdgeDistributionBuilder` is used to define target nodes or clusters for edges in a graph. It provides
 * methods to create node references and group them into clusters, facilitating customized edge target definitions.
 * This class extends `STMTBuilder` to manage a list of statements and implements `LiteralBuilder` for creating literals.
 *
 * The `@ASTDslMarker` annotation is used to limit the DSL scope when building edge distribution structures.
 *
 * @param body A lambda function executed during initialization to define the builder's configurations.
 */
@ASTDslMarker
class EdgeDistributionBuilder(
    body: EdgeDistributionBuilder.() -> Unit
) : STMTBuilder<EdgeDistribution>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Creates a reference to a graph node, optionally specifying its port and compass point.
     *
     * @param id The identifier of the node being referenced. Represents the unique ID of the node.
     * @param port Optional port information for the node. This specifies subcomponents or subdivisions within the node.
     * @param compass Optional compass point for the node, indicating the relative direction (e.g., north, south, etc.) or positioning of the node.
     */
    fun nodeRefOf(
        id: Literal,
        port: Literal? = null,
        compass: Literal? = null,
    ) = NodeRef(
        id = id,
        port = port,
        compass = compass,
    )

    /**
     * Adds a reference to a graph node in the current builder's statement list. This reference can optionally
     * include specification of a port and a compass point for more precise targeting.
     *
     * @param id The identifier of the node being referenced. Represents the unique ID of the node.
     * @param port Optional port information for the node. This specifies subcomponents or subdivisions within the node.
     * @param compass Optional compass point for the node, indicating the relative direction (e.g., north, south, etc.) or positioning of the node.
     */
    fun nodeRef(
        id: Literal,
        port: Literal? = null,
        compass: Literal? = null,
    ) {
        stmts += nodeRefOf(id = id, port = port, compass = compass)
    }

    /**
     * Adds a collection of node references associated with a cluster to the current
     * builder's statement list. These references are grouped together as a `ClusterNodeRefs`
     * object, representing clusters in a graph structure.
     *
     * @param refs A variable number of `NodeRef` instances representing the nodes to be
     * included in the cluster.
     */
    fun clusterNodeRefs(
        vararg refs: NodeRef,
    ) {
        stmts += ClusterNodeRefs(refs.toList())
    }
}

/**
 * A builder class designed for constructing Graph Statements (GraphSTMT) within a DOT graph.
 *
 * The `GraphSTMTBuilder` is specifically used in the construction of the Abstract Syntax Tree (AST)
 * representation of a DOT graph, enabling the definition of nodes, edges, attributes, subgraphs,
 * comments, and attribute groups. It provides a DSL-style approach for organizing and configuring
 * these components.
 *
 * This builder is part of a structured DSL environment, marked by the `@ASTDslMarker` annotation,
 * which ensures isolated scope usage and prevents access to outer contexts.
 *
 * @constructor Creates a `GraphSTMTBuilder` instance, invoking the provided `body` lambda in its
 * DSL context to initialize and add statements to the AST structure.
 *
 * @param body A lambda function allowing for the declaration of graph statements within the builder.
 */
@ASTDslMarker
class GraphSTMTBuilder(body: GraphSTMTBuilder.() -> Unit) : STMTBuilder<GraphSTMT>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Appends a comment to the list of statements in the graph's structure.
     *
     * @param value The textual content of the comment.
     * @param kind The type of comment, specified by the `Comment.Kind` enum. Defaults to `Comment.Kind.SLASH`.
     */
    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    /**
     * Defines a node in the graph with the specified ID and attributes.
     *
     * This function allows the creation of a node in the graph by specifying a unique
     * identifier and providing a body block to define its attributes. The attributes are
     * configured through the `AttributeSTMTBuilder` and can include custom key-value
     * pairs or comments related to the node.
     *
     * @param id The unique identifier for the node, represented as a `Literal`.
     * @param body A lambda receiver of type `AttributeSTMTBuilder` used to define the attributes of the node.
     */
    fun node(
        id: Literal,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += Node(
            id = id,
            children = AttributeSTMTBuilder(body).stmts
        )
    }

    /**
     * Adds an attribute to the graph's structure, represented as a key-value pair.
     *
     * @param key The key of the attribute, represented as a `Literal`.
     * @param value The value of the attribute, associated with the key, represented as a `Literal`.
     */
    fun attribute(
        key: Literal,
        value: Literal,
    ) {
        stmts += Attribute(
            key = key,
            value = value,
        )
    }

    /**
     * Defines a subgraph within the graph structure.
     *
     * This function allows the creation of a subgraph by specifying an optional identifier
     * and a block of initialization logic to configure its content. The subgraph can
     * contain nodes, edges, attributes, comments, or additional nested subgraphs.
     *
     * @param id An optional identifier for the subgraph, represented as a `Literal`.
     *           This identifier can be used to uniquely name or reference the subgraph.
     * @param init A lambda receiver for `GraphSTMTBuilder` used to define the content
     *             of the subgraph, including nodes, edges, attributes, and more.
     */
    fun subgraph(
        id: Literal?,
        init: GraphSTMTBuilder.() -> Unit,
    ) {
        stmts += Subgraph(
            id = id,
            children = GraphSTMTBuilder(init).stmts,
        )
    }

    /**
     * Defines and adds an `AttributeGroup` to the graph's structure with a specified kind and attributes.
     *
     * This function facilitates the creation of attribute groups in the graph, specifying their type
     * through the `kind` parameter and allowing the customization of their attributes using the provided
     * lambda function `body`.
     *
     * @param kind The category or type of the attribute group, as defined by `AttributeGroup.Kind`.
     *             Specifies whether the group pertains to the graph, nodes, or edges.
     * @param body A lambda receiver of type `AttributeSTMTBuilder`, allowing the definition
     *             of attributes included in the group.
     */
    fun attributeGroup(
        kind: AttributeGroup.Kind,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += AttributeGroup(
            kind = kind,
            children = AttributeSTMTBuilder(body).stmts
        )
    }

    /**
     * Creates an edge in the graph by specifying its target nodes or clusters and its attributes.
     *
     * This function defines an edge using two separate configuration blocks: one for the edge's
     * targets (nodes or clusters it connects) and another for the edge's attributes (key-value
     * pairs or comments specific to the edge). The structure of the edge is added to the list
     * of statements within the graph's syntax tree.
     *
     * @param targets A lambda receiver of type `EdgeDistributionBuilder` used to define the target
     *                nodes or clusters connected by the edge.
     * @param body A lambda receiver of type `AttributeSTMTBuilder` used to define the attributes
     *             specific to the edge.
     */
    fun edge(
        targets: EdgeDistributionBuilder.() -> Unit,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += Edge(
            targets = EdgeDistributionBuilder(targets).stmts,
            children = AttributeSTMTBuilder(body).stmts,
        )
    }
}

/**
 * A builder class used for constructing DOT language statements in an Abstract Syntax Tree (AST) format.
 *
 * `DotSTMTBuilder` facilitates the creation and accumulation of various statements,
 * such as comments and root graphs, within the DOT graph representation. It serves as
 * a domain-specific language (DSL) entry-point for defining the structural elements
 * and relationships in a DOT graph.
 *
 * This builder is marked with the `@ASTDslMarker` annotation to ensure isolation of
 * its DSL context. It extends `STMTBuilder` with `DotSTMT` as the base type and
 * implements the `LiteralBuilder` interface to provide utility methods for working
 * with graph-related literals.
 *
 * @constructor Configures a new instance of `DotSTMTBuilder` with the provided initialization block.
 * In the block, DSL functions can be invoked to define statements.
 *
 * @param body An initialization block where DSL functions can be used to construct DOT statements.
 */
@ASTDslMarker
class DotSTMTBuilder(body: DotSTMTBuilder.() -> Unit) : STMTBuilder<DotSTMT>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Appends a new comment to the list of statements in the DOT AST builder.
     *
     * @param value The content of the comment to be added.
     * @param kind The type of the comment, specified as one of the predefined kinds in `Comment.Kind`.
     *             Defaults to `Comment.Kind.SLASH` if not explicitly specified.
     */
    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    /**
     * Appends a root graph to the current list of statements in the DOT AST builder.
     *
     * A root graph represents the entry point for defining a graph in the DOT language.
     * It can be configured as directed or undirected, and strict mode can be enabled
     * to enforce certain constraints according to the DOT specification. The graph
     * structure is defined in the provided initialization block.
     *
     * @param id An optional identifier for the root graph. Can be a literal value or null if no identifier is specified.
     * @param directed Indicates whether the graph is directed (`true`) or undirected (`false`).
     * @param strict Specifies whether the graph is strict (`true`) or not. Strict graphs prevent parallel edges between nodes.
     * @param init An initialization block where the graph's structure and properties are defined using a `GraphSTMTBuilder`.
     */
    fun rootGraph(
        id: Literal?,
        directed: Boolean,
        strict: Boolean,
        init: GraphSTMTBuilder.() -> Unit,
    ) {
        stmts += RootGraph(
            id = id,
            directed = directed,
            strict = strict,
            children = GraphSTMTBuilder(init).stmts
        )
    }
}

/**
 * Builds a `Dot` instance using the specified initialization block.
 *
 * This method provides a DSL (Domain Specific Language) entry-point for constructing the
 * root of a DOT Abstract Syntax Tree (AST) through the `DotSTMTBuilder`. The initialization block
 * allows defining various DOT statements such as comments, graphs, nodes, and edges.
 *
 * @param init An initialization block used to configure the DOT graph statements. This block
 *             operates on a `DotSTMTBuilder` context, providing DSL functions to define
 *             the structure and properties of the graph.
 * @return A `Dot` instance representing the root AST element that encapsulates the constructed
 *         DOT statements.
 */
fun dot(
    init: DotSTMTBuilder.() -> Unit,
): Dot = Dot(
    DotSTMTBuilder(init).stmts
)
