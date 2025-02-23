package com.github.kamiazya.graphviz

import kotlin.test.*

class CompassTest {
    @Test
    fun `Compass's from method returns correct Compass enum`() {
        assertEquals(Compass.N, Compass.from("n"))
        assertEquals(Compass.NE, Compass.from("ne"))
        assertEquals(Compass.E, Compass.from("e"))
        assertEquals(Compass.SE, Compass.from("se"))
        assertEquals(Compass.S, Compass.from("s"))
        assertEquals(Compass.SW, Compass.from("sw"))
        assertEquals(Compass.W, Compass.from("w"))
        assertEquals(Compass.NW, Compass.from("nw"))
        assertEquals(Compass.C, Compass.from("c"))
    }

    @Test
    fun `Compass's from method throws IllegalArgumentException for unsupported value`() {
        assertFailsWith<IllegalArgumentException> {
            Compass.from("unsupported")
        }
    }
}

class AttributeTest {
    @Test
    fun `Attribute class correctly handles key-value pairs`() {
        val attribute = Attribute("color", "red")
        assertEquals("color", attribute.key)
        assertEquals("red", attribute.value)
    }

    @Test
    fun `Attribute class correctly handles key-value pairs with destructuring`() {
        val (key, value) = Attribute("color", "red")
        assertEquals("color", key)
        assertEquals("red", value)
    }
}

class HasAttributesTest {
    class TestModel : HasAttributes {
        override var attributes: List<Attribute> = mutableListOf()
    }

    @Test
    fun `HasAttributes interface correctly manages attributes`() {
        val model = TestModel()
        model.setAttribute("color", "red")
        assertEquals("red", model.getAttribute<String>("color"))

        model.setAttribute("size", 10)
        assertEquals(10, model.getAttribute<Int>("size"))

        assertTrue(model.exists("color"))
        assertTrue(model.exists("size"))

        model.removeAttribute("color")
        assertTrue(!model.exists("color"))

        model.clear()
        assertTrue(!model.exists("size"))
    }
}

class ForwardRefNodeTest {
    @Test
    fun `ForwardRefNode initializes correctly`() {
        val node = ForwardRefNode(id = "node1", port = "port1", compass = Compass.N)
        assertEquals("node1", node.id)
        assertEquals("port1", node.port)
        assertEquals(Compass.N, node.compass)
    }

    @Test
    fun `ForwardRefNode initializes correctly with null values`() {
        val node = ForwardRefNode(id = "node1")
        assertEquals("node1", node.id)
        assertNull(node.port)
        assertNull(node.compass)
    }

    @Test
    fun `ForwardRefNode class correctly handles id-port-compass pairs with destructuring`() {
        val (id, port, compass) = ForwardRefNode("node", "port", Compass.N)
        assertEquals("node", id)
        assertEquals("port", port)
        assertEquals(Compass.N, compass)
    }

    @Test
    fun `ForwardRefNode from method works correctly`() {
        val node = ForwardRefNode.from("a:port:n")
        assertEquals("a", node.id)
        assertEquals("port", node.port)
        assertEquals(Compass.N, node.compass)
    }

    @Test
    fun `ForwardRefNode from method throws IllegalArgumentException for invalid ref`() {
        assertFailsWith<IllegalArgumentException> {
            ForwardRefNode.from("invalid:ref:format")
        }
    }
}

class AttributeValueOfTest {
    open class TestModel : AttributeGroupModel {
        override var attributes: List<Attribute> = mutableListOf()
    }

    @Test
    fun `AttributeValueOf gets and sets values correctly`() {
        val model = object : TestModel() {
            var someAttributeValue: String? by AttributeValueOf()
        }
        model.someAttributeValue = "testValue"
        assertEquals("testValue", model.someAttributeValue)
    }

    @Test
    fun `AttributeValueOf gets and sets values correctly with default value`() {
        val model = object : TestModel() {
            var someAttributeValue: String? by AttributeValueOf(defaultValue="defaultValue")
        }
        assertEquals("defaultValue", model.someAttributeValue)
        model.someAttributeValue = "testValue"
        assertEquals("testValue", model.someAttributeValue)
    }

    @Test
    fun `AttributeValueOf gets and sets values correctly with default value and custom getter`() {
        val model = object : TestModel() {
            var someAttributeValue: String? by AttributeValueOf() { it.uppercase() }
        }
        model.someAttributeValue = "testValue"
        assertEquals("TESTVALUE", model.someAttributeValue)
    }
}

class DotTest {
    @Test
    fun `Dot initializes correctly`() {
        val dot = Dot(comment = "This is a dot model")
        assertEquals("This is a dot model", dot.comment)
        assertNull(dot.root)
        assertEquals(ModelContext.default, dot.context)
    }

    @Test
    fun `Dot initializes correctly with default values`() {
        val dot = Dot()
        assertNull(dot.comment)
        assertNull(dot.root)
        assertEquals(ModelContext.default, dot.context)
    }
}

// class BaseGraphTest {
//     @Test
//     fun `BaseGraph initializes correctly`() {
//         val baseGraph = object : BaseGraph(id = "graph1") {}
//         assertEquals("graph1", baseGraph.id)
//         assertNull(baseGraph.comment)
//         assertEquals(emptyList<NodeModel>(), baseGraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), baseGraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), baseGraph.subgraphs)
//         assertEquals(GraphAttributeGroup(), baseGraph.graphAttributes)
//         assertEquals(NodeAttributeGroup(), baseGraph.nodeAttributes)
//         assertEquals(EdgeAttributeGroup(), baseGraph.edgeAttributes)
//         assertEquals(ModelContext.default, baseGraph.context)
//     }

//     @Test
//     fun `BaseGraph initializes correctly with default values`() {
//         val baseGraph = object : BaseGraph(id = null) {}
//         assertNull(baseGraph.id)
//         assertNull(baseGraph.comment)
//         assertEquals(emptyList<NodeModel>(), baseGraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), baseGraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), baseGraph.subgraphs)
//         assertTrue(baseGraph.graphAttributes is GraphAttributeGroup)
//         assertTrue(baseGraph.nodeAttributes is NodeAttributeGroup)
//         assertTrue(baseGraph.edgeAttributes is EdgeAttributeGroup)
//         assertEquals(ModelContext.default, baseGraph.context)
//     }
// }

class NodeAttributeGroupTest {
    @Test
    fun `NodeAttributeGroup initializes correctly`() {
        val nodeAttributeGroup = NodeAttributeGroup()
        assertNull(nodeAttributeGroup.shape)
        assertNull(nodeAttributeGroup.color)
        assertEquals(emptyList<Attribute>(), nodeAttributeGroup.attributes)
    }

    @Test
    fun `NodeAttributeGroup initializes correctly with default values`() {
        val nodeAttributeGroup = NodeAttributeGroup()
        assertNull(nodeAttributeGroup.shape)
        assertNull(nodeAttributeGroup.color)
        assertEquals(emptyList<Attribute>(), nodeAttributeGroup.attributes)
    }
}

class EdgeAttributeGroupTest {
    @Test
    fun `EdgeAttributeGroup initializes correctly`() {
        val edgeAttributeGroup = EdgeAttributeGroup()
        assertNull(edgeAttributeGroup.color)
        assertEquals(emptyList<Attribute>(), edgeAttributeGroup.attributes)
    }

    @Test
    fun `EdgeAttributeGroup initializes correctly with default values`() {
        val edgeAttributeGroup = EdgeAttributeGroup()
        assertNull(edgeAttributeGroup.color)
        assertEquals(emptyList<Attribute>(), edgeAttributeGroup.attributes)
    }
}

class GraphAttributeGroupTest {
    @Test
    fun `GraphAttributeGroup initializes correctly`() {
        val graphAttributeGroup = GraphAttributeGroup()
        assertNull(graphAttributeGroup.color)
        assertEquals(emptyList<Attribute>(), graphAttributeGroup.attributes)
    }

    @Test
    fun `GraphAttributeGroup initializes correctly with default values`() {
        val graphAttributeGroup = GraphAttributeGroup()
        assertNull(graphAttributeGroup.color)
        assertEquals(emptyList<Attribute>(), graphAttributeGroup.attributes)
    }
}

// class RootGraphTest {
//     @Test
//     fun `RootGraph initializes correctly`() {
//         val rootGraph = object : RootGraph(directed = true, strict = false, id = "rootGraph1") {}
//         assertTrue(rootGraph.directed)
//         assertFalse(rootGraph.strict)
//         assertEquals("rootGraph1", rootGraph.id)
//         assertNull(rootGraph.comment)
//         assertEquals(emptyList<NodeModel>(), rootGraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), rootGraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), rootGraph.subgraphs)
//         assertEquals(GraphAttributeGroup(), rootGraph.graphAttributes)
//         assertEquals(NodeAttributeGroup(), rootGraph.nodeAttributes)
//         assertEquals(EdgeAttributeGroup(), rootGraph.edgeAttributes)
//         assertEquals(ModelContext.default, rootGraph.context)
//     }

//     @Test
//     fun `RootGraph initializes correctly with default values`() {
//         val rootGraph = object : RootGraph(directed = false, strict = false, id = null) {}
//         assertFalse(rootGraph.directed)
//         assertFalse(rootGraph.strict)
//         assertNull(rootGraph.id)
//         assertNull(rootGraph.comment)
//         assertEquals(emptyList<NodeModel>(), rootGraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), rootGraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), rootGraph.subgraphs)
//         assertEquals(GraphAttributeGroup(), rootGraph.graphAttributes)
//         assertEquals(NodeAttributeGroup(), rootGraph.nodeAttributes)
//         assertEquals(EdgeAttributeGroup(), rootGraph.edgeAttributes)
//         assertEquals(ModelContext.default, rootGraph.context)
//     }
// }

// class GraphTest {
//     @Test
//     fun `Graph initializes correctly`() {
//         val graph = Graph(strict = true, id = "graph1")
//         assertFalse(graph.directed)
//         assertTrue(graph.strict)
//         assertEquals("graph1", graph.id)
//         assertNull(graph.comment)
//         assertEquals(emptyList<NodeModel>(), graph.nodes)
//         assertEquals(emptyList<EdgeModel>(), graph.edges)
//         assertEquals(emptyList<SubgraphModel>(), graph.subgraphs)
//         assertEquals(GraphAttributeGroup(), graph.graphAttributes)
//         assertEquals(NodeAttributeGroup(), graph.nodeAttributes)
//         assertEquals(EdgeAttributeGroup(), graph.edgeAttributes)
//         assertEquals(ModelContext.default, graph.context)
//     }

//     @Test
//     fun `Graph initializes correctly with default values`() {
//         val graph = Graph(strict = false, id = null)
//         assertFalse(graph.directed)
//         assertFalse(graph.strict)
//         assertNull(graph.id)
//         assertNull(graph.comment)
//         assertEquals(emptyList<NodeModel>(), graph.nodes)
//         assertEquals(emptyList<EdgeModel>(), graph.edges)
//         assertEquals(emptyList<SubgraphModel>(), graph.subgraphs)
//         assertEquals(GraphAttributeGroup(), graph.graphAttributes)
//         assertEquals(NodeAttributeGroup(), graph.nodeAttributes)
//         assertEquals(EdgeAttributeGroup(), graph.edgeAttributes)
//         assertEquals(ModelContext.default, graph.context)
//     }
// }

// class DigraphTest {
//     @Test
//     fun `Digraph initializes correctly`() {
//         val digraph = Digraph(strict = true, id = "digraph1")
//         assertTrue(digraph.directed)
//         assertTrue(digraph.strict)
//         assertEquals("digraph1", digraph.id)
//         assertNull(digraph.comment)
//         assertEquals(emptyList<NodeModel>(), digraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), digraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), digraph.subgraphs)
//         assertEquals(GraphAttributeGroup(), digraph.graphAttributes)
//         assertEquals(NodeAttributeGroup(), digraph.nodeAttributes)
//         assertEquals(EdgeAttributeGroup(), digraph.edgeAttributes)
//         assertEquals(ModelContext.default, digraph.context)
//     }

//     @Test
//     fun `Digraph initializes correctly with default values`() {
//         val digraph = Digraph(strict = false, id = null)
//         assertTrue(digraph.directed)
//         assertFalse(digraph.strict)
//         assertNull(digraph.id)
//         assertNull(digraph.comment)
//         assertEquals(emptyList<NodeModel>(), digraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), digraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), digraph.subgraphs)
//         assertEquals(GraphAttributeGroup(), digraph.graphAttributes)
//         assertEquals(NodeAttributeGroup(), digraph.nodeAttributes)
//         assertEquals(EdgeAttributeGroup(), digraph.edgeAttributes)
//         assertEquals(ModelContext.default, digraph.context)
//     }
// }

class NodeTest {
    @Test
    fun `Node initializes correctly`() {
        val node = Node(id = "node1", comment = "This is a node")
        assertEquals("node1", node.id)
        assertEquals("This is a node", node.comment)
        assertNull(node.shape)
        assertNull(node.color)
        assertEquals(emptyList<Attribute>(), node.attributes)
    }

    @Test
    fun `Node initializes correctly with default values`() {
        val node = Node(id = "node1")
        assertEquals("node1", node.id)
        assertNull(node.comment)
        assertNull(node.shape)
        assertNull(node.color)
        assertEquals(emptyList<Attribute>(), node.attributes)
    }
}

class EdgeTest {
    @Test
    fun `Edge initializes correctly`() {
        val node1 = ForwardRefNode("node1")
        val node2 = ForwardRefNode("node2")
        val edge = Edge(listOf(node1, node2), comment = "This is an edge")
        assertEquals(listOf(node1, node2), edge.targets)
        assertEquals("This is an edge", edge.comment)
        assertNull(edge.color)
        assertEquals(emptyList<Attribute>(), edge.attributes)
    }

    @Test
    fun `Edge throws IllegalArgumentException when targets size is less than 2`() {
        assertFailsWith<IllegalArgumentException> {
            Edge(listOf())
        }

        assertFailsWith<IllegalArgumentException> {
            val node1 = ForwardRefNode("node1")
            Edge(listOf(node1))
        }
    }
}

// class SubgraphTest {
//     @Test
//     fun `Subgraph initializes correctly`() {
//         val subgraph = Subgraph(id = "subgraph1", comment = "This is a subgraph")
//         assertEquals("subgraph1", subgraph.id)
//         assertEquals("This is a subgraph", subgraph.comment)
//         assertEquals(emptyList<Attribute>(), subgraph.attributes)
//         assertEquals(emptyList<NodeModel>(), subgraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), subgraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), subgraph.subgraphs)
//         assertEquals(ModelContext.default, subgraph.context)
//     }

//     @Test
//     fun `Subgraph initializes correctly with default values`() {
//         val subgraph = Subgraph()
//         assertNull(subgraph.id)
//         assertNull(subgraph.comment)
//         assertEquals(emptyList<Attribute>(), subgraph.attributes)
//         assertEquals(emptyList<NodeModel>(), subgraph.nodes)
//         assertEquals(emptyList<EdgeModel>(), subgraph.edges)
//         assertEquals(emptyList<SubgraphModel>(), subgraph.subgraphs)
//         assertEquals(ModelContext.default, subgraph.context)
//     }
// }
