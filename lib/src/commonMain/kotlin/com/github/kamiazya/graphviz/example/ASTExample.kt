package com.github.kamiazya.graphviz.example

import com.github.kamiazya.graphviz.ast.AST
import com.github.kamiazya.graphviz.ast.dsl.dot

var asts: List<AST> = listOf(
    dot {
        comment("AA")

        rootGraph(
            strict = true,
            directed = true,
            id = "hoge".unquated()
        ) {
            node("node1".unquated()) {
                attribute("color".unquated(), "red".quated())
            }

            edge({
                nodeRef("a1".unquated())
                nodeRef("a2".unquated())

                clusterNodeRefs(
                    nodeRefOf("b".unquated()),
                    nodeRefOf("c".unquated())
                )
            }) {
                attribute("color".unquated(), "blue".quated())
            }

            subgraph(id = null) {
                node("node2".quated()) {}
            }
        }
    }
)
