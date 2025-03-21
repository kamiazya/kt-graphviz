package com.github.kamiazya.graphviz.type

/**
 * Represents the cardinal and inter cardinal compass directions, as well as a central point.
 *
 * The `Compass` enum provides a set of predefined constants for commonly used directions:
 * - Cardinal directions: `N` (North), `E` (East), `S` (South), `W` (West)
 * - Inter cardinal directions: `NE` (Northeast), `SE` (Southeast), `SW` (Southwest), `NW` (Northwest)
 * - Center: `C`
 *
 * This enum also includes utility functions for working with compass directions, including
 * conversion from a string representation.
 */
enum class Compass {
    N, NE, E, SE, S, SW, W, NW, C;

    companion object {
        /**
         * Converts a string representation of a compass direction into a `Compass` enum value.
         *
         * The supported values are:
         * - "n" for `N`
         * - "ne" for `NE`
         * - "e" for `E`
         * - "se" for `SE`
         * - "s" for `S`
         * - "sw" for `SW`
         * - "w" for `W`
         * - "nw" for `NW`
         * - "c" for `C`
         *
         * @param value The string representation of the compass direction.
         * @return The corresponding `Compass` enum value.
         * @throws IllegalArgumentException If the provided value does not match any supported compass directions.
         */
        fun from(value: String): Compass = when (value) {
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

    /**
     * Returns the lowercase string representation of the enum constant's name.
     *
     * @return A lowercase string representing the name of the enum constant.
     */
    override fun toString(): String = name.lowercase()
}
