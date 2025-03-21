package com.github.kamiazya.graphviz.model

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A property delegate to manage attributes of objects implementing the `HasAttributes` interface.
 *
 * @param T The type of the object implementing the `HasAttributes` interface.
 * @param V The type of the attribute value being managed.
 * @property actualName The custom name of the attribute. If `null`, the property name will be used.
 * @property defaultValue An optional default value for the attribute.
 * @property modifier An optional lambda to modify the value before setting it as an attribute.
 *
 * ## Example
 *
 * ### simple case
 * ```
 * class TestModel : HasAttributes {
 *    var color: String? by AttributeValueOf()
 * }
 * ```
 *
 * ### with actual name
 * ```
 * class TestModel : HasAttributes {
 *   var color: String? by AttributeValueOf("actual_color")
 * }
 *
 * val model = TestModel()
 * model.color = "red"
 * model.getAttribute("actual_color") // "red"
 * model.color // "red"
 * ```
 *
 * ### with default value
 * ```
 * class TestModel : HasAttributes {
 *  var color: String? by AttributeValueOf(defaultValue = "red")
 * }
 *
 * val model = TestModel()
 * model.color // "red"
 * ```
 */
class AttributeValueOf<T, V>(
    private val actualName: String? = null,
    private val defaultValue: V? = null,
    private val modifier: ((V) -> V)? = null,
) : ReadWriteProperty<T, V?>
    where T : HasAttributes {

    /**
     * Provides a delegate instance for the property.
     * This function initializes the property with a default value, and associates the delegate with the property.
     *
     * @param thisRef The object for which the property is being delegated.
     * @param property Metadata for the property to which the delegate is associated.
     * @return The current instance of AttributeValueOf with the delegate provided.
     */
    operator fun provideDelegate(thisRef: T, property: KProperty<*>): AttributeValueOf<T, V> {
        setValue(thisRef, property, defaultValue)
        return this
    }

    /**
     * Retrieves the value of a specified attribute for the provided property.
     *
     * @param thisRef The object for which the property is being delegated.
     * @param property Metadata for the associated property.
     * @return The value of the attribute, or null if the attribute is not found.
     */
    override operator fun getValue(thisRef: T, property: KProperty<*>): V? {
        return thisRef.getAttribute(actualName ?: property.name)
    }

    /**
     * Sets the value of an attribute for the given property. If the value is null, the attribute is removed.
     *
     * @param thisRef The object for which the property is being delegated.
     * @param property Metadata for the property to which the delegate is associated.
     * @param value The value to set for the attribute. If null, the attribute will be removed.
     */
    override operator fun setValue(thisRef: T, property: KProperty<*>, value: V?) {
        if (value == null) {
            thisRef.removeAttribute(actualName ?: property.name)
        } else {
            thisRef.setAttribute(actualName ?: property.name, modifier?.invoke(value) ?: value)
        }
    }
}
