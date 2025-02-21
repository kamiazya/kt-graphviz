package com.github.kamiazya.graphviz

/**
 * AttributeValue is a value of an attribute.
 */
// public sealed interface AttributeValue {
//     // companion object {
//     //     fun of(value: Any): AttributeValue = when (value) {
//     //         is String -> StringAttributeValue(value)
//     //         is Int -> IntAttributeValue(value)
//     //         is Float -> FloatAttributeValue(value)
//     //         is Boolean -> BoolAttributeValue(value)
//     //         else -> throw IllegalArgumentException("Unsupported type: ${value::class.simpleName}")
//     //     }

//     //     public class StringAttributeValue(val value: String) : AttributeValue

//     //     public class IntAttributeValue(val value: Int) : AttributeValue

//     //     public class FloatAttributeValue(val value: Float) : AttributeValue

//     //     public class BoolAttributeValue(val value: Boolean) : AttributeValue
//     // }
// }

/**
 * Compass is a direction of a node.
 */
public enum class Compass {
    N, NE, E, SE, S, SW, W, NW, C;

    public companion object {
        /**
         * Create a Compass from a string.
         * @param value A string value.
         * @return A Compass.
         * @throws IllegalArgumentException if the value is unsupported.
         */
        public fun from(value: String): Compass = when (value) {
            "n" -> N
            "ne" -> NE
            "e" -> E
            "se" -> SE
            "s" -> S
            "sw" -> SW
            "w" -> W
            "nw" -> NW
            "c" -> C
            else -> throw IllegalArgumentException("Unsupported value: $value")
        }
    }
}

// TODO
class Color(val value: String) {
    override fun toString(): String = value
}
