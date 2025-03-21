package com.github.kamiazya.graphviz.model

/**
 * Interface representing an entity with an identifiable ID.
 *
 * The `HasID` interface provides a contract for associating an ID
 * string with an implementing entity. This ID serves as a unique
 * identifier within the context of the application or domain, enabling
 * operations such as lookups, comparisons, or other ID-based logic.
 *
 * Features:
 * - A mutable `id` property of type `String` to define or modify the identifier.
 * - Use cases may include nodes, edges, or other graph elements in
 *   a structure where unique identification is required.
 */
public interface HasID {
    /**
     * A mutable property representing the unique identifier for an entity.
     *
     * The `id` property is designed to store a string that serves as a unique
     * identifier for objects implementing the `HasID` interface. It enables
     * operations like lookup, comparison, and management of entities within
     * a graph or other structured collections.
     *
     * This property is mutable, allowing the identifier to be updated if required.
     */
    var id: String
}
