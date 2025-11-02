package me.coderfrish.test

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.api.Parser
import me.coderfrish.toml.reader.TomlLexer
import me.coderfrish.toml.reader.TomlParser
import kotlin.test.Test

class LexerTest {
    @Test
    fun testLexer() {
        val toml = """
            # This header comments.
            gfdgdfgd=-1000_000_000_000 # Hello World!!
            testfdfs=+1546546
            test=true
            1234=156
            name="Frish2021"
            "This a key"='Hello World!!'
        """.trimIndent()

        val lexer: Lexer = TomlLexer(toml)
        val parser: Parser = TomlParser(lexer)
        parser.parse()
    }
}
