package me.coderfrish.test

import me.coderfrish.toml.TomlLexer
import me.coderfrish.toml.TomlParser
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            age=15.5515213
            admin=true
            name="Frish2021"
            message-text="Hello World!!!"
        """.trimIndent()
        val lexer = TomlLexer(toml)
        val parser = TomlParser(lexer)
        val parse = parser.parse()

        println(parse["name"])
        println(parse["age"] is Float)
    }

    @Test
    fun stringTest() {
        val toml = "\"Hello World!!!\""

        val array = toml.toCharArray()
        var offset = 0
        var str = ""
        offset += 1
        while (offset < array.size - 1) {
            str += array[offset]
            offset++
        }
        println(str)
    }
}