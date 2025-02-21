package com.github.kamiazya.graphviz

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.test.assertNull

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

    @Test
    fun `Commpass is interface of AttributeValue`() {
        val compass: AttributeValue = Compass.N
        assertTrue(compass is AttributeValue)
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
