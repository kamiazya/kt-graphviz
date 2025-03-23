package com.github.kamiazya.graphviz.model

/**
 * Interface representing an entity with an optional or nullable ID.
 *
 * The `HasNullableID` interface defines a contract for handling an optional
 * identifier within an entity. This nullable `id` property allows for
 * scenarios where the entity may or may not have an assigned identifier.
 *
 * Key Features:
 * - A mutable `id` property of type `String?`, which can either store a unique
 *   identifier or remain null if no ID is assigned.
 * - Enables flexibility in managing entities where identification is either
 *   temporarily absent or not required in certain contexts.
 * - Useful in data structures or models where optional identification is
 *   applicable, such as intermediate states or optional graph elements.
 *
 * Implementing classes or interfaces can leverage this nullable property
 * to provide dynamic or conditional assignment of identifiers while ensuring
 * compatibility with systems requiring optional ID handling.
 */
interface HasNullableID {
    /**
     * A mutable property representing an optional identifier for an entity.
     *
     * The `id` property is designed to store a nullable string that acts as a unique identifier
     * for objects implementing the `HasNullableID` interface. This identifier can be used for
     * referencing, comparing, or managing entities within a system. Since the property is nullable,
     * it accommodates scenarios where an identifier may not be assigned or required.
     */
    var id: String?
}
