package me.coderfrish.toml.tokens


/**
 * @author Frish2021
 */
data class TomlToken(val type: TokenType, val value: String) {
    constructor(type: TokenType) :
            this(type, type.name)
}
