package me.coderfrish.toml.tokens

data class TomlToken(val type: TokenType, val value: String) {
    constructor(type: TokenType) :
            this(type, type.name)
}
