package me.coderfrish.toml.api

import me.coderfrish.toml.tokens.TomlToken

interface Lexer {
    fun tokenize(): List<TomlToken>
}
