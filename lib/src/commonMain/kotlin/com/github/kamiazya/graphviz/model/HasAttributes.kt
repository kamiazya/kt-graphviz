package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.type.AttributeValue

/**
 * An interface representing a model that has a collection of attributes.
 *
 * The `HasAttributes` interface provides mechanisms for accessing,
 * modifying, and managing attributes, which are key-value pairs
 * represented by the `Attribute` class. Each key is a unique string
 * identifier, and the corresponding value is derived from the `AttributeValue` type.
 */
interface HasAttributes {
    /**
     * A list of attributes associated with a graph model.
     *
     * Each attribute in the list is represented by the `Attribute` class, which consists
     * of a key-value pair. The key is a unique string identifier, and the value is of type
     * `AttributeValue`. This variable allows interaction with attributes, such as querying,
     * modifying, or adding new attributes to the model.
     */
    var attributes: List<Attribute>

    /**
     * Retrieve an attribute value by its key.
     *
     * This function searches the `attributes` collection for an entry matching the specified key.
     * If a matching key is found, the associated value is cast to the desired type and returned.
     * If the key does not exist, the function returns null.
     *
     * @param key The key of the attribute to retrieve.
     * @return The value of the attribute associated with the specified key, cast to the desired type,
     *         or null if the key does not exist.
     */
    fun <T : AttributeValue> getAttribute(key: String): T? = attributes.find { it.key == key }?.value?.let {
        @Suppress("UNCHECKED_CAST")
        return it as T
    }

    /**
     * Sets an attribute with the specified key and value.
     *
     * If an attribute with the given key already exists, its value will be updated to the new value.
     * If no attribute with the given key exists, a new attribute will be added to the collection.
     *
     * @param key The key of the attribute to set or update.
     * @param value The value to associate with the specified key.
     *        The value must extend the `AttributeValue` type.
     * @param T The type of the attribute value, which must extend `AttributeValue`.
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
     * Removes an attribute from the collection based on the specified key.
     *
     * This function searches the `attributes` collection for an attribute with a matching key.
     * If a match is found, the attribute is removed from the collection.
     *
     * @param key The key of the attribute to remove.
     */
    fun removeAttribute(key: String) {
        attributes.find { it.key == key }?.let {
            attributes -= it
        }
    }

    /**
     * Checks if an attribute with the specified key exists within the attributes collection.
     *
     * @param key The key of the attribute to check for existence.
     * @return True if an attribute with the specified key exists, otherwise false.
     */
    fun exists(key: String): Boolean = attributes.any { it.key == key }

    /**
     * Clears all attributes from the collection.
     *
     * This method resets the `attributes` collection to an empty list, effectively
     * removing any existing attributes. Use this to completely clear all associated
     * attributes from the object.
     */
    fun clear() {
        attributes = emptyList()
    }
}
