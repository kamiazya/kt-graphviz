package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.AST

public interface Model<T : AST> {
    fun toAST(): List<T>
}
