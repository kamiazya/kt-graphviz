package com.github.kamiazya.graphviz.ast

/**
 * Represents a hierarchical relationship within an Abstract Syntax Tree (AST).
 *
 * The `Parents` interface allows a structural component to maintain a list of child
 * elements, which are themselves part of the AST. These child elements conform to
 * the generic type `STMT`, which must be a subclass of `AST`.
 *
 * Classes implementing `Parents` act as containers for their child elements,
 * facilitating the construction and traversal of a tree-like structure within
 * the AST representation of a DOT graph or other hierarchical constructs.
 *
 * @param STMT The type of the child elements, which must extend the `AST` base interface.
 * Classes or interfaces defining this parameter align the container's content with their
 * specific structural needs.
 *
 * @property children A mutable list of child elements adhering to the `STMT` type. This
 * list defines the hierarchical structure of the parent within the AST by containing
 * its immediate descendants.
 */
interface Parents<STMT : AST> {
    var children: MutableList<STMT>
}
