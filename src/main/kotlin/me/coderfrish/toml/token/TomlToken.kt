package me.coderfrish.toml.token

data class TomlToken(val type: TokenType, val value: String) {
    constructor(type: TokenType, value: StringBuilder) :
            this(type, value.toString())

    constructor(type: TokenType) :
            this(type, type.name)
}
