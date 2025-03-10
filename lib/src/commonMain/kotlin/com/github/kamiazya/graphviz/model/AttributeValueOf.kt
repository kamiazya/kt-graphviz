package com.github.kamiazya.graphviz.model

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * AttributeValueOf is a delegate for an attribute value.
 *
 * @param T A type of the model.
 * @param V A type of the attribute value.
 * @param actualName An actual name of the attribute.
 * @param defaultValue A default value of the attribute.
 * @return A delegate for an attribute value.
 *
 * @example simple case
 * ```
 * class TestModel : AttributeGroupModel {
 *    var color: String? by AttributeValueOf()
 * }
 * ```
 *
 * @example with actual name
 * ```
 * class TestModel : AttributeGroupModel {
 *   var color: String? by AttributeValueOf("actual_color")
 * }
 *
 * val model = TestModel()
 * model.color = "red"
 * model.getAttribute("actual_color") // "red"
 * model.color // "red"
 * ```
 *
 * @example with default value
 * ```
 * class TestModel : AttributeGroupModel {
 *  var color: String? by AttributeValueOf(defaultValue = "red")
 * }
 *
 * val model = TestModel()
 * model.color // "red"
 * ```
 */
public class AttributeValueOf<T, V>(
    /**
     * An actual name of the attribute.
     */
    private val actualName: String? = null,

    /**
     * A default value of the attribute.
     */
    private val defaultValue: V? = null,
    /**
     * A modifier of the attribute.
     */
    private val modifier: ((V) -> V)? = null,
) : ReadWriteProperty<T, V?>
    where T : AttributeGroupModel {

    /**
     * When property is delegated, set the default value.
     */
    operator fun provideDelegate(thisRef: T, property: KProperty<*>): AttributeValueOf<T, V> {
        setValue(thisRef, property, defaultValue)
        return this
    }

    /**
     * When property is accessed, get the attribute value.
     */
    override operator fun getValue(thisRef: T, property: KProperty<*>): V? {
        return thisRef.getAttribute(actualName ?: property.name)
    }

    /**
     * When property is set, set the attribute value.
     * If the value is null, remove the attribute.
     * If the modifier is set, apply the modifier to the value before setting value.
     *
     */
    override operator fun setValue(thisRef: T, property: KProperty<*>, value: V?) {
        if (value == null) {
            thisRef.removeAttribute(actualName ?: property.name)
        } else {
            thisRef.setAttribute(actualName ?: property.name, modifier?.invoke(value) ?: value)
        }
    }
}
