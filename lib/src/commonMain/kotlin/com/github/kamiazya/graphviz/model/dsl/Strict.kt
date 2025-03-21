package com.github.kamiazya.graphviz.model.dsl

/**
 * Represents a marker object used in graph DSLs to signify the creation of "strict" graph structures.
 *
 * This object facilitates the creation of strict graphs or strict directed graphs, ensuring that
 * duplicate edges are disallowed within the graph description. It is used indirectly through infix
 * functions or parameters in graph DSL contexts.
 */
@Suppress("ClassNaming")
public object strict
