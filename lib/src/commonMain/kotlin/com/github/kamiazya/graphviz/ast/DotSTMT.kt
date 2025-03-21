package com.github.kamiazya.graphviz.ast

/**
 * Represents a statement element in the DOT Abstract Syntax Tree (AST).
 *
 * DotSTMT is a sealed interface used to define various structural
 * components within a DOT graph such as comments, graphs, and other
 * hierarchical elements. It acts as a unifying type for all statements
 * directly part of a DOT graph's declaration.
 *
 * Examples of implementing classes or interfaces include:
 * - RootGraph: The main graph in the DOT representation.
 * - Comment: A representation of a comment in a DOT file.
 *
 * This interface ensures that all DOT-specific statements conform
 * to the common AST structure while remaining extensible for
 * diverse DOT element types.
 */
sealed interface DotSTMT : AST
