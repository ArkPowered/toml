package me.coderfrish.test

import me.coderfrish.toml.TomlParser
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            admin=true
            name="Frish2021" 
            message.text="Hello World!!!"
        """.trimIndent()
        val parser = TomlParser(toml)
        parser.parse()
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