package me.coderfrish.test

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.api.Parser
import me.coderfrish.toml.reader.TomlLexer
import me.coderfrish.toml.reader.TomlParser
import me.coderfrish.toml.shared.NumberUtils
import kotlin.test.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            # Number Test
            number=inf
        """.trimIndent()

        val lexer: Lexer = TomlLexer(toml)
        val parser: Parser = TomlParser(lexer)
        val parse = parser.parse()
        println(parse["number"] == Double.POSITIVE_INFINITY)
    }

    @Test
    fun testRegex() {
        val str = "0B010100"
        println(NumberUtils.toNumber(str))
    }
}
