package me.coderfrish.test

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.reader.TomlLexer
import kotlin.test.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            number=0xC
        """.trimIndent()

        val lexer: Lexer = TomlLexer(toml)
        for (token in lexer.tokenize()) {
            println("${token.type} | ${token.value}")
        }
//        val parser: Parser = TomlParser(lexer)
//        parser.parse()
    }
}
