package me.coderfrish.toml.reader

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.base.BaseParser
import me.coderfrish.toml.shared.NumberUtils
import me.coderfrish.toml.tokens.TokenType
import me.coderfrish.toml.tokens.TomlToken

class TomlParser(lexer: Lexer): BaseParser(lexer) {
    override fun identifier(key: TomlToken) {
        advance()
        if (peek().type == TokenType.EQUALS) {
            advance() /* 跳过等于号 */
            value(key.value, peek())
        }
        advance()
    }

    fun value(key: String, token: TomlToken) {
        when (val type = token.type) {
            TokenType.NUMBER -> {
                this.number(key, token)
            }

            TokenType.STRING -> {
                this.string(key, token)
            }

            else -> {
                error("Unknown token $type")
            }
        }
    }

    fun string(key: String, token: TomlToken) {
        entries[key] = token.value
    }

    fun number(key: String, token: TomlToken) {
        entries[key] = NumberUtils.toNumber(token.value)
    }
}
