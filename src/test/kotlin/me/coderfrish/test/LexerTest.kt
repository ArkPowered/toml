package me.coderfrish.test

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.reader.TomlLexer
import kotlin.test.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            gfdgdfgd=-1000_000_000_000
            testfdfs=+1546546
            test=true
            1234=156
            name="Frish2021"
            "This a key"='Hello World!!'
        """.trimIndent()

        val lexer: Lexer = TomlLexer(toml)
//        lexer.tokenize()
        for (token in lexer.tokenize()) {
            println("${token.type} | ${token.value}")
        }
    }
}
