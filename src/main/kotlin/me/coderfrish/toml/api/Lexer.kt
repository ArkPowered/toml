package me.coderfrish.toml.api

import me.coderfrish.toml.tokens.TomlToken

/**
 * @author Frish2021
 * @see me.coderfrish.toml.base.BaseLexer
 */
interface Lexer {
    /**
     * @author Frish2021
     * @return Token List
     */
    fun tokenize(): List<TomlToken>
}
