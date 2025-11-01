package me.coderfrish.test

import me.coderfrish.toml.reader.TomlLexer
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            age=123456
            name="Frish2021"
            message="Hello World!!"
        """.trimIndent()

        /**
         * admin=true
         * name="Frish2021"
         * message-text="Hello World!!!"
         */

        val lexer = TomlLexer(toml)
        for (token in lexer.tokenize()) {
            println("${token.type} | ${token.value}")
        }
    }
}