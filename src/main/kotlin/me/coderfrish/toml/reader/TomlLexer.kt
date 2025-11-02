package me.coderfrish.toml.reader

import me.coderfrish.toml.base.BaseLexer
import me.coderfrish.toml.shared.IDENTIFIER_PATTERN
import me.coderfrish.toml.shared.MatchUtils.matches
import me.coderfrish.toml.tokens.TokenType.ENTER
import me.coderfrish.toml.tokens.TokenType.STRING
import me.coderfrish.toml.tokens.TokenType.EQUALS
import me.coderfrish.toml.tokens.TomlToken
import me.coderfrish.toml.shared.QUOTA_NOT_CONSISTENT_EXCEPTION
import me.coderfrish.toml.tokens.TokenType

class TomlLexer(str: String) : BaseLexer(str) {
    override fun parseIdentifier(): TomlToken {
        val builder = StringBuilder()
        while (matches(peek(), IDENTIFIER_PATTERN)) {
            builder.append(peekWithAdvance())
        }

        return TomlToken(TokenType.IDENTIFIER, builder)
    }

    override fun parseEnter(): TomlToken {
        advance()
        return TomlToken(ENTER)
    }

    override fun parseEquals(): TomlToken {
        advance()
        return TomlToken(EQUALS, "=")
    }

    override fun parseString(char: Char): TomlToken {
        this.advance()
        val builder = StringBuilder()
        while (peek() != '"' && peek() != '\'') {
            builder.append(peekWithAdvance())
        }
        this.ensureStringQuotaConsistent(char)
        this.advance()
        return TomlToken(STRING, builder.toString())
    }

    private fun ensureStringQuotaConsistent(char: Char) {
        if (peek() != char) error(QUOTA_NOT_CONSISTENT_EXCEPTION)
    }
}
