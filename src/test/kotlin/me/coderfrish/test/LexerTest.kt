package me.coderfrish.test

import me.coderfrish.toml.reader.TomlLexer
import me.coderfrish.toml.reader.TomlParser
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            admin=true
            age=15
            name="Frish2021"
            message="Hello World!!"
        """.trimIndent()

        /**
         * admin=true
         * name="Frish2021"
         * message-text="Hello World!!!"
         */

        val lexer = TomlLexer(toml)
        val parser = TomlParser(lexer)
        val mapper = parser.parse()
        println(mapper["admin"] is Boolean)
    }
}