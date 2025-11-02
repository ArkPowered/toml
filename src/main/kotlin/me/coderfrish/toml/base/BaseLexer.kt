package me.coderfrish.toml.base

import java.util.LinkedList
import me.coderfrish.toml.api.Lexer
import me.coderfrish.toml.tokens.TokenType
import me.coderfrish.toml.tokens.TokenType.ENTER
import me.coderfrish.toml.tokens.TomlToken
import me.coderfrish.toml.tokens.TokenType.EOF
import me.coderfrish.toml.tokens.TokenType.EQUALS

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

    protected fun isTokenEmpty(): Boolean =
        this.tokens.isEmpty()

    protected fun isNotTokenEmpty(): Boolean =
        this.tokens.isNotEmpty()

    protected fun lastToken(): TomlToken =
        tokens.last()

    protected fun lastValue(): String =
        lastToken().value

    protected fun lastType(): TokenType =
        tokens.last().type

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
        tokens.add(TomlToken(EOF))

        return tokens
    }

    fun parseEnter(): TomlToken {
        advance()
        return TomlToken(ENTER)
    }

    fun parseEquals(): TomlToken {
        advance()
        return TomlToken(EQUALS, "=")
    }

    abstract fun parseIdentifier(): TomlToken

    abstract fun parseString(char: Char): TomlToken
}
