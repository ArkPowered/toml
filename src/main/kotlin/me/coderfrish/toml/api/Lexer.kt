package me.coderfrish.toml.api

import me.coderfrish.toml.token.TomlToken

interface Lexer {
    fun tokenize(): List<TomlToken>
}
