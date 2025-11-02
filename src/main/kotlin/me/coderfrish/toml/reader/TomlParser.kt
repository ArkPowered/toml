package me.coderfrish.toml.reader

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.base.BaseParser
import me.coderfrish.toml.tokens.TokenType

class TomlParser(lexer: Lexer): BaseParser(lexer) {
    override fun parseIdentifier() {
        val key = peek().value
        advance()

        if (peek().type == TokenType.EQUALS) {
            advance()

            val value = peek().value
            println("key: $key, value: $value")
        }

        advance()
    }
}
