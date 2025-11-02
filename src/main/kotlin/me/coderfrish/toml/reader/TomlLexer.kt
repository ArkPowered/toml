package me.coderfrish.toml.reader

import me.coderfrish.toml.base.BaseLexer
import me.coderfrish.toml.tokens.TokenType
import me.coderfrish.toml.tokens.TomlToken
import me.coderfrish.toml.shared.BOOLEAN_PATTERN
import me.coderfrish.toml.shared.IDENTIFIER_PATTERN
import me.coderfrish.toml.shared.MatchUtils.matches
import me.coderfrish.toml.tokens.TokenType.STRING
import me.coderfrish.toml.tokens.TokenType.NUMBER
import me.coderfrish.toml.tokens.TokenType.EQUALS
import me.coderfrish.toml.tokens.TokenType.BOOLEAN
import me.coderfrish.toml.tokens.TokenType.IDENTIFIER
import me.coderfrish.toml.shared.QUOTA_NOT_CONSISTENT_EXCEPTION


/**
 * @author Frish2021
 */
class TomlLexer(str: String) : BaseLexer(str) {
    override fun parseIdentifier(): TomlToken {
        val builder = StringBuilder()
        while (offset < str.length && matches(peek(), IDENTIFIER_PATTERN)) {
            builder.append(peekWithAdvance())
        }

        return parseIdentifierToken(builder.toString())
    }

    private fun parseIdentifierToken(string: String): TomlToken =
        TomlToken(parseIdentifierType(string), string)

    private fun parseIdentifierType(string: String): TokenType =
        if (isNotTokenEmpty() && lastType().equals(EQUALS)) {
            if (matches(string, BOOLEAN_PATTERN))
                BOOLEAN
            else
                NUMBER
        } else {
            IDENTIFIER
        }

    /* 除了解析字符串那边是一大坨其他都还行把... */
    override fun parseString(char: Char): TomlToken {
        this.advance()
        val builder = StringBuilder()
        while (offset < str.length && (peek() != '"' && peek() != '\'')) {
            if (peek() == '\\') {
                builder.append(parseEscapeSequence())
            } else {
                builder.append(peekWithAdvance())
            }
        }
        if (peek() != char) error(QUOTA_NOT_CONSISTENT_EXCEPTION)
        this.advance()

        return parseStringToken(builder.toString())
    }

    private fun parseStringToken(string: String): TomlToken =
        TomlToken(parseStringType(), string)

    private fun parseStringType(): TokenType =
        if (isNotTokenEmpty() && lastType().equals(EQUALS)) {
            STRING
        } else {
            IDENTIFIER
        }

    private fun parseEscapeSequence(): Char {
        advance()
        return when (val escapeChar = peek()) {
            'b' -> { advance(); '\b' }
            't' -> { advance(); '\t' }
            'n' -> { advance(); '\n' }
            'f' -> { advance(); '\u000C' }
            'r' -> { advance(); '\r' }
            '"' -> { advance(); '"' }
            '\\' -> { advance(); '\\' }
            'u' -> parseUnicodeEscape(4)
            'U' -> parseUnicodeEscape(8)
            else -> error("Invalid escape sequence: \\$escapeChar")
        }
    }

    private fun parseUnicodeEscape(digits: Int): Char {
        advance()
        val hex = str.substring(offset, offset + digits)
        try {
            val codePoint = hex.toInt(16)
            repeat(digits) { advance() }
            return codePoint.toChar()
        } catch (e: NumberFormatException) {
            error("Invalid Unicode escape sequence: \\u$hex")
        }
    }
}
