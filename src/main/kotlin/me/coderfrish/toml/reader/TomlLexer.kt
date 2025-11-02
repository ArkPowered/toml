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
import me.coderfrish.toml.shared.MatchUtils.isRawNumber
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
            else if (isRawNumber(string))
                NUMBER
            else
                IDENTIFIER
        } else {
            IDENTIFIER
        }

    /* 除了解析字符串那边是一大坨其他都还行把... */
    override fun parseString(char: Char): TomlToken {
        this.advance()
        val builder = StringBuilder()
        while (offset < str.length && (peek() != '"' && peek() != '\'')) {
            builder.append(peekWithAdvance())
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
}
