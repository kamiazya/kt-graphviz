package com.github.kamiazya.graphviz.type

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

    override fun toString(): String = name.lowercase()
}
