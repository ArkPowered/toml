package me.coderfrish.toml.tokens

/**
 * @author Frish2021
 */
enum class TokenType {
    EOF, EQUALS, ENTER,

    STRING, NUMBER, BOOLEAN, IDENTIFIER;

    fun equals(type: TokenType): Boolean =
        this == type
}
