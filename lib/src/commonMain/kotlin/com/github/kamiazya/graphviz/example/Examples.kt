package com.github.kamiazya.graphviz.example

import com.github.kamiazya.graphviz.ast.AST
import com.github.kamiazya.graphviz.ast.from
import com.github.kamiazya.graphviz.dot
import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.dsl.strict
import com.github.kamiazya.graphviz.type.Compass

val dots: List<DotModel> = listOf(
    dot {
        comment = "This is a sample graph."

        graph("graphID") {

            node {
                color = "red"
            }
            val a = node("a") {
                comment = "This is a sample node a."

                color = "blue"
            }

            val b = node("b") {
                comment = "This is a sample node b."
            }

            var c = ref("c")

            edge(a, b.toRef("port_of_a", Compass.N), ref("C:s")) {
                color = "red"
            }

            (a - b - c) {
                color = "red"
            }

            ("a" - (a and c)) {
                color = "red"
            }

            ("a" - b - "c") {
                color = "red"
            }

            ("a" - b.toRef("port_of_a", Compass.N) - "c") {
                color = "red"
            }

            ("a" - b.toRef("port_of_a", Compass.N) - c) {
                color = "red"
            }
        }
    },
    dot {
        comment = "This is a sample graph."
        strict digraph {
            node {
                color = "blue"
            }
        }
    },
    dot {
        comment = "This is a sample graph."
        strict.digraph("graphID") {
            node {
                color = "red"
            }

            edge {
                color = "red"
            }

            node("a") {
                comment = "This is a sample node a."
                color = "blue"
            }
        }
    },
    dot {
        comment = "This is a sample graph."
        strict graph {
            node {
                color = "blue"
            }
        }
    },
    dot {
        comment = "This is a sample graph."
        strict.graph("graphID") {
            node {
                color = "red"
            }

            edge {
                color = "red"
            }

            node("a") {
                comment = "This is a sample node a."
                color = "blue"
            }
        }
    }
)

val asts: List<AST> = dots.map { from(it) }
