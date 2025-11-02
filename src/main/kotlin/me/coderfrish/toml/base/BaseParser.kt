package me.coderfrish.toml.base

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.api.Parser
import me.coderfrish.toml.tokens.TokenType
import me.coderfrish.toml.tokens.TomlToken

abstract class BaseParser(lexer: Lexer): Parser {
    private val tokens = lexer.tokenize()
    private var offset = 0
    protected val entries = LinkedHashMap<String, Any>()

    protected fun peek(): TomlToken =
        this.tokens[this.offset]

    protected fun advance() {
        this.offset++
    }

    protected fun advanceWithPeek(): TomlToken =
        this.tokens[++this.offset]

    protected fun peekWithAdvance(): TomlToken =
        this.tokens[this.offset++]

    override fun parse(): Map<String, Any> {
        while (offset < tokens.size) {
            this.parse0()
        }

        return entries
    }

    private fun parse0() {
        when (peek().type) {
            TokenType.IDENTIFIER -> {
                this.parseIdentifier()
            }

            TokenType.ENTER,
            TokenType.EOF-> {
                advance()
            }

            else -> {
                this.unknownToken()
            }
        }
    }

    abstract fun parseIdentifier()

    private fun unknownToken(): Nothing =
        error("Unknown token type ${peek().type}")
}
