package com.github.kamiazya.graphviz.ast

/**
 * Represents the base sealed interface for all Abstract Syntax Tree (AST) elements
 * used to model structures in a DOT graph representation.
 *
 * The `AST` interface is implemented by various other interfaces and classes
 * to define specific components, attributes, and relationships within the
 * graph structure.
 *
 * Classes and interfaces that extend `AST` define elements such as:
 *
 * - Attributes for nodes, edges, or graphs
 * - Comments, literal values, or attribute groups
 * - Graph structures such as root graphs or subgraphs
 * - Parent-child relationships between AST components
 *
 * This interface allows a unified type system for all elements in the DOT graph's AST,
 * enabling modularity and extensibility of its components.
 */
sealed interface AST

/**
 * Represents a marker interface for all attribute-related statements within the Abstract Syntax Tree (AST)
 * of a DOT graph.
 *
 * Classes implementing this interface define specific attribute-related elements, such as:
 * - Single attributes that define key-value pairs.
 * - Groups of attributes categorized by types like graph, node, or edge.
 * - Comments associated with the attributes.
 *
 * This interface allows attribute-related elements to be distinguished and grouped within the greater structure
 * of a DOT graph representation.
 */
sealed interface AttributesSTMT : AST

/**
 * Represents a statement element in the DOT Abstract Syntax Tree (AST).
 *
 * DotSTMT is a sealed interface used to define various structural
 * components within a DOT graph such as comments, graphs, and other
 * hierarchical elements. It acts as a unifying type for all statements
 * directly part of a DOT graph's declaration.
 *
 * Examples of implementing classes or interfaces include:
 * - RootGraph: The main graph in the DOT representation.
 * - Comment: A representation of a comment in a DOT file.
 *
 * This interface ensures that all DOT-specific statements conform
 * to the common AST structure while remaining extensible for
 * diverse DOT element types.
 */
sealed interface DotSTMT : AST

/**
 * Represents a Graph Statement used in the construction of a DOT graph Abstract Syntax Tree (AST).
 *
 * A `GraphSTMT` is a sealed interface that defines various structural components of a graph such as:
 * - Nodes
 * - Edges
 * - Attributes
 * - Subgraphs
 * - Comments
 *
 * Implementations of `GraphSTMT` align with specific elements in a DOT graph's structure
 * and are used to describe their relationships and attributes in the graph hierarchy.
 *
 * This interface is commonly utilized in graph models that involve parsing or generating ASTs
 * for DOT language representations.
 */
sealed interface GraphSTMT : AST

/**
 * Represents a hierarchical relationship within an Abstract Syntax Tree (AST).
 *
 * The `Parents` interface allows a structural component to maintain a list of child
 * elements, which are themselves part of the AST. These child elements conform to
 * the generic type `STMT`, which must be a subclass of `AST`.
 *
 * Classes implementing `Parents` act as containers for their child elements,
 * facilitating the construction and traversal of a tree-like structure within
 * the AST representation of a DOT graph or other hierarchical constructs.
 *
 * @param STMT The type of the child elements, which must extend the `AST` base interface.
 * Classes or interfaces defining this parameter align the container's content with their
 * specific structural needs.
 *
 * @property children A mutable list of child elements adhering to the `STMT` type. This
 * list defines the hierarchical structure of the parent within the AST by containing
 * its immediate descendants.
 */
interface Parents<STMT : AST> {
    var children: MutableList<STMT>
}

/**
 * Represents the root of a DOT Abstract Syntax Tree (AST).
 *
 * The `Dot` class is the primary container for a collection of `DotSTMT` elements,
 * which define the structure and content of a DOT graph.
 * It implements the `AST` interface, ensuring conformance to the AST hierarchy,
 * and the `Parents` interface, allowing it to maintain hierarchical relationships with its child elements.
 *
 * This class is typically used within DSLs or programmatic representations of DOT files.
 *
 */
data class Dot(
    override var children: MutableList<DotSTMT>,
) : AST, Parents<DotSTMT>

/**
 * Represents a comment within a DOT graph's Abstract Syntax Tree (AST).
 *
 * The `Comment` class is used to include comments in a DOT graph representation.
 * A comment can be associated with various statement types such as attributes,
 * nodes, edges, or graph definitions. Comments can provide additional
 * explanations or metadata for the graph structure in a non-executable format.
 *
 * @param value The textual content of the comment.
 * @param kind The type of comment, specified by the `Kind` enum.
 */
data class Comment(
    var value: String,
    var kind: Kind,
) : AST, AttributesSTMT, DotSTMT, GraphSTMT {
    /**
     * Represents different kinds of comments that can exist in a DOT graph's Abstract Syntax Tree (AST).
     *
     * The `Kind` class defines the style or type of comment in the graph representation.
     * It is a sealed class to ensure that all possible types of comments are explicitly defined.
     *
     * Types of `Kind` include:
     * - `BLOCK`: Represents a block comment style, often used for multi-line comments.
     * - `SLASH`: Represents a single-line comment style, prefixed by slashes.
     * - `MACRO`: Represents a macro comment, which may indicate preprocessing directives or macro-like behavior.
     */
    sealed class Kind {
        /**
         * Represents a block comment style in a DOT graph's Abstract Syntax Tree (AST).
         *
         * Block comments are typically used for multi-line annotations or descriptions
         * within the graph representation.
         */
        data object BLOCK : Kind()

        /**
         * Represents a single-line comment style in a DOT graph's Abstract Syntax Tree (AST).
         *
         * Single-line comments are prefixed by slashes and are typically used to add brief
         * annotations or remarks within the graph representation.
         */
        data object SLASH : Kind()
        /**
         * Represents a macro comment style in a DOT graph's Abstract Syntax Tree (AST).
         *
         * Macro comments may be used to represent preprocessing directives or macro-like behavior
         * within the graph representation.
         */
        data object MACRO : Kind()
    }
}

/**
 * Represents a group of attributes categorized by their type within a DOT graph Abstract Syntax Tree (AST).
 *
 * The `AttributeGroup` class implements the `AST`, `GraphSTMT`, and `Parents` interfaces, indicating that it:
 * - Acts as an element of the AST used to represent the structure of a DOT graph.
 * - Serves as a graph statement contributing to the definition of graph nodes, edges, or attributes.
 * - Behaves as a parent container managing a collection of child `AttributesSTMT` elements.
 *
 * This class provides the ability to group attributes by their kind, which is defined using the `Kind` enum.
 * The `kind` property specifies the type of attributes the group represents, such as graph, node, or edge attributes.
 *
 * @property kind Represents the type of attributes the group contains (e.g., GRAPH, NODE, EDGE).
 * @property children A mutable list of `AttributesSTMT` elements that belong to this attribute group.
 */
data class AttributeGroup(
    var kind: Kind,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT> {
    /**
     * Represents the types of attribute groups in a DOT graph.
     *
     * This sealed class defines the possible kinds of attribute groups based on their scope or association.
     * It is used to categorize attributes as being associated with the graph, nodes, or edges.
     *
     * - GRAPH: Attributes associated with the entire graph.
     * - NODE: Attributes associated with graph nodes.
     * - EDGE: Attributes associated with graph edges.
     *
     * This classification is used in the Abstract Syntax Tree (AST) representation of DOT graphs
     * to organize attributes effectively and enforce their usage context.
     */
    sealed class Kind {
        /**
         * Represents the kind of attributes associated with the entire graph in a DOT representation.
         *
         * The `GRAPH` kind is used to specify global attributes that apply to the entire graph
         * within the Abstract Syntax Tree (AST) for DOT graphs. These attributes encompass metadata or
         * styling information that applies universally to the graph and is not limited to specific nodes or edges.
         *
         * This object serves as a constant in the `Kind` sealed class hierarchy, allowing the categorization
         * of attributes by their scope of application.
         */
        data object GRAPH : Kind()
        /**
         * Represents the kind of attributes associated with graph nodes in a DOT representation.
         *
         * The `NODE` kind is used to specify attributes that apply to nodes within the Abstract Syntax Tree (AST)
         * for DOT graphs. These attributes can include node-specific styles, metadata, or other characteristics
         * that define the visual or logical properties of a node in the graph.
         *
         * This object serves as a constant in the `Kind` sealed class hierarchy, enabling the categorization
         * of attributes by their scope of application to nodes.
         */
        data object NODE : Kind()
        /**
         * Represents the `edge` kind used for defining and categorizing
         * edge-specific attributes or functionality within the graph model system.
         *
         * This object is part of the `Kind` classification, specifically utilized
         * in contexts where edges of a graph, their attributes, or associated
         * data are referenced.
         */
        data object EDGE : Kind()
    }
}

/**
 * Represents the root graph in a DOT graph's Abstract Syntax Tree (AST).
 *
 * RootGraph is a data class that models the main graph structure in a DOT representation.
 * It encapsulates properties such as the graph's identifier, its directed/undirected nature,
 * strict mode, and a list of child statements that define the graph's structure and attributes.
 *
 * @property id The literal identifier of the graph. It can be null to represent an anonymous graph.
 * @property directed A boolean indicating whether the graph is directed (true) or undirected (false).
 * @property strict A boolean indicating whether the graph enforces a strict structure, which disallows
 * duplicate edges or parallel edges in the graph structure.
 * @property children A mutable list of GraphSTMT elements representing the child statements within the graph.
 * These may include nodes, edges, attributes, subgraphs, or other graph statements.
 */
data class RootGraph(
    var id: Literal? = null,
    var directed: Boolean,
    var strict: Boolean,
    override var children: MutableList<GraphSTMT>,
) : AST, DotSTMT, Parents<GraphSTMT>

/**
 * Represents a subgraph within a DOT graph's Abstract Syntax Tree (AST).
 *
 * A `Subgraph` is a structural component used to define a nested graph
 * or a grouping of nodes and edges within the larger graph structure.
 * It is commonly used to apply attributes or organize nodes and edges
 * in a hierarchical or modular way.
 *
 * @property id An optional identifier for the subgraph, defined as a `Literal`.
 * If present, this identifier is used to uniquely name or reference the subgraph
 * within the scope of the graph.
 *
 * @property children A mutable list of graph statements (`GraphSTMT`) that
 * represent the content of the subgraph. This includes nodes, edges,
 * attributes, comments, or additional nested subgraphs.
 */
data class Subgraph(
    var id: Literal? = null,
    override var children: MutableList<GraphSTMT>,
) : AST, GraphSTMT, Parents<GraphSTMT>

/**
 * Represents an attribute key-value pair in a DOT graph Abstract Syntax Tree (AST).
 *
 * The `Attribute` class models an individual attribute consisting of a key and a value,
 * both represented as `Literal` instances. Attributes are typically used to define
 * properties or metadata for graph components such as nodes, edges, or graphs.
 *
 * This class implements the `AST`, `AttributesSTMT`, and `GraphSTMT` interfaces, making
 * it a part of both the attribute-specific and general graph-related statement hierarchies.
 *
 * @property key The key of the attribute, represented as a `Literal`.
 * @property value The value associated with the key, represented as a `Literal`.
 */
data class Attribute(
    var key: Literal,
    var value: Literal,
) : AST, AttributesSTMT, GraphSTMT

/**
 * Represents a literal value in a DOT graph's Abstract Syntax Tree (AST).
 *
 * A `Literal` is used to model values like graph identifiers, node or edge names,
 * attribute keys/values, and other concrete string elements in the DOT language.
 * It supports different types of quoting styles to allow flexible representation
 * of values, including:
 *
 * - `QUOTED`: Indicates the value is explicitly quoted.
 * - `UNQUOTED`: Indicates the value is not quoted.
 * - `HTML`: Indicates the value is treated as HTML content.
 *
 * @property value The string value of the literal.
 * @property quoted The type of quoting style applied to the literal.
 */
data class Literal(
    var value: String,
    var quoted: Quote = Quote.QUOTED,
) : AST {
    /**
     * Represents the quoting style of a literal value in the DOT graph's Abstract Syntax Tree (AST).
     *
     * The `Quote` sealed class defines the possible quoting styles that can be applied
     * to a literal value. This is used to determine how the value is formatted within
     * the graph representation.
     *
     * Types of quoting styles:
     * - `QUOTED`: Indicates the value is explicitly enclosed in quotes.
     * - `UNQUOTED`: Indicates the value is presented without quotes.
     * - `HTML`: Indicates the value is treated as HTML content, enabling HTML-specific formatting.
     */
    sealed class Quote {
        /**
         * Represents a quoting style where the value is explicitly enclosed in quotes.
         *
         * The `QUOTED` quoting style is a part of the `Quote` sealed class and is used to specify
         * that a literal value in the DOT graph's Abstract Syntax Tree (AST) should be formatted
         * with enclosing quote characters. This ensures proper representation of values that may
         * require quoting due to special characters or reserved keywords.
         */
        data object QUOTED : Quote()
        /**
         * Represents a quoting style where the value is presented without enclosing quotes.
         *
         * The `UNQUOTED` quoting style is part of the `Quote` sealed class and indicates
         * that a literal value in the DOT graph's Abstract Syntax Tree (AST) should be
         * formatted without enclosing quotation marks. This is typically used for values
         * that do not require quoting due to their simplicity or syntax compliance.
         */
        data object UNQUOTED : Quote()
        /**
         * Represents a quoting style where the value is treated as HTML content.
         *
         * The `HTML` quoting style is a part of the `Quote` sealed class and is used to
         * specify that a literal value in the DOT graph's Abstract Syntax Tree (AST)
         * should be interpreted and formatted as HTML content. This allows for
         * HTML-specific representations within a graph, such as HTML-like formatting in
         * node labels.
         */
        data object HTML : Quote()
    }
}

/**
 * Represents a Node in the Abstract Syntax Tree (AST) of a DOT graph.
 *
 * A `Node` defines a specific component in the graph, identified by a unique `id`
 * and associated with a list of attribute-related statements (`children`).
 *
 * The `id` property uniquely identifies the node, represented by a `Literal` value,
 * which can specify its quoting style and string content. The `children` property
 * contains a mutable list of `AttributesSTMT`, which allows the node to define
 * specific attributes or other attribute-related groupings.
 *
 * This structure is typically used to model and manipulate nodes and their
 * associated attributes in a DOT graph representation, supporting tree-like
 * traversals and relationships within the broader AST.
 */
data class Node(
    var id: Literal,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT>

/**
 * Represents an interface for defining edge distribution elements in a DOT graph.
 *
 * Edge distribution refers to the representation of edge targets in a graph,
 * which can be either individual node references or groups of node references.
 *
 * Implementations of this interface can model various structures such as:
 * - A single node reference, including optional port and compass information
 * - A group of node references, such as clusters or aggregates
 * - Targets for graph edges, describing source and destination relationships
 *
 * This interface is typically used to unify different types of edge-related components
 * in the Abstract Syntax Tree (AST) of a graph structure.
 */
interface EdgeDistribution : AST

/**
 * Represents a reference to a graph node in the DOT language's Abstract Syntax Tree (AST).
 *
 * This class is primarily used to model node references for edges in a graph,
 * including optional port information and compass points.
 *
 * NodeRef combines the ability to reference specific parts of a graph structure,
 * such as nodes and their sub-elements, with flexibility for additional configuration.
 *
 * @property id The identifier of the node being referenced.
 * @property port Optional port information for the node, which specifies
 * subcomponents within the node.
 * @property compass Optional compass point for the node, used to indicate the
 * direction relative to the node's geometry.
 */
data class NodeRef(
    var id: Literal,
    var port: Literal? = null,
    var compass: Literal? = null,
) : AST, EdgeDistribution

/**
 * Represents a collection of node references associated with a cluster in a DOT graph.
 *
 * This class is used to model groups of `NodeRef` elements that correspond to clusters
 * or aggregates of graph nodes, specifically for use in edge targeting or other edge-related
 * operations within a graph's Abstract Syntax Tree (AST).
 *
 * As a part of the AST, `ClusterNodeRefs` allows the representation of multiple node references
 * as a single entity, providing a way to logically group or cluster nodes together for further
 * processing or rendering in a graph structure.
 *
 * @property refs A list of `NodeRef` instances representing the nodes included in this cluster.
 */
data class ClusterNodeRefs(
    var refs: List<NodeRef>,
) : AST, EdgeDistribution

/**
 * Represents an edge in a DOT graph's Abstract Syntax Tree (AST).
 *
 * The `Edge` class models relationships between graph elements by defining edge connections
 * through its target nodes and associated attributes. It combines multiple functionalities
 * from the `AST`, `GraphSTMT`, and `Parents` interfaces to allow integration into the greater
 * graph representation structure.
 *
 * @property targets A list of `EdgeDistribution` instances that specify the target nodes or
 * groups of nodes connected by the edge.
 * @property children A mutable list of child elements conforming to the `AttributesSTMT` type,
 * representing edge-specific attributes.
 */
data class Edge(
    var targets: List<EdgeDistribution>,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT>
