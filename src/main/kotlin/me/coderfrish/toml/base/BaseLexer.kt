package me.coderfrish.toml.base

import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.token.TomlToken
import java.util.LinkedList

abstract class BaseLexer(protected val str: String) : Lexer {
    protected val tokens = LinkedList<TomlToken>()
    protected var offset = 0

    protected fun peek(): Char =
        this.str[this.offset]

    protected fun advance() {
        this.offset++
    }

    protected fun advanceWithPeek(): Char =
        this.str[++this.offset]

    protected fun peekWithAdvance(): Char =
        this.str[this.offset++]

    private fun tokenize0() {
        when (peek()) {
            '"', '\'' -> {
                tokens.add(parseString(
                    peek()
                ))
            }

            '\n' -> {
                tokens.add(parseEnter())
            }

            '=' -> {
                tokens.add(parseEquals())
            }

            else -> {
                tokens.add(parseIdentifier())
            }
        }
    }

    override fun tokenize(): List<TomlToken> {
        while (offset < str.length) {
            this.tokenize0()
        }

        return tokens
    }

    abstract fun parseIdentifier(): TomlToken

    abstract fun parseEnter(): TomlToken

    abstract fun parseEquals(): TomlToken

    abstract fun parseString(char: Char): TomlToken
}
