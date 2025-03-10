package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.type.AttributeValue

/**
 * HasAttributes is an interface for models that have attributes.
 */
public interface HasAttributes {
    /**
     * A list of attributes.
     */
    var attributes: List<Attribute>

    /**
     * Get an attribute value by key.
     * @param key A key of the attribute.
     * @return An attribute value.
     */
    fun <T : AttributeValue> getAttribute(key: String): T? = attributes.find { it.key == key }?.value?.let {
        @Suppress("UNCHECKED_CAST")
        return it as T
    }

    /**
     * Set an attribute value by key.
     * @param key A key of the attribute.
     * @param value A value of the attribute.
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
     * Remove an attribute by key.
     * @param key A key of the attribute.
     */
    fun removeAttribute(key: String) {
        attributes.find { it.key == key }?.let {
            attributes -= it
        }
    }

    /**
     * Check if an attribute exists by key.
     * @param key A key of the attribute.
     * @return true if the attribute exists.
     *        false if the attribute does not exist.
     */
    fun exists(key: String): Boolean = attributes.any { it.key == key }

    /**
     * Clear all attributes.
     */
    fun clear() {
        attributes = emptyList()
    }
}
