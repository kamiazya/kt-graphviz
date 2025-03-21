package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AST

/**
 * Represents a generic model that can be converted into an Abstract Syntax Tree (AST) representation.
 *
 * The `Model` interface defines the contract for classes that are capable of producing
 * a structured AST representation through the `toAST` method. This enables a standardized
 * way to generate and retrieve an AST composed of elements conforming to the `AST` type.
 *
 * @param T The type of elements in the AST, constrained to types that implement the `AST` interface.
 */
public interface Model<T : AST> {
    /**
     * Converts the current model into an Abstract Syntax Tree (AST) representation.
     *
     * This method generates a list of AST elements of a specific type, representing
     * the structured form of the model's data. It serves as a transformation mechanism
     * to bridge the gap between a model's internal state and its abstract representation.
     *
     * @return A list of AST elements of type T, representing the abstract syntax tree
     *         structure derived from the model.
     */
    fun toAST(): List<T>
}
