package me.coderfrish.toml.tokens

enum class TokenType {
    EOF, EQUALS, ENTER, Test,

    STRING, NUMBER, BOOLEAN, IDENTIFIER;

    fun equals(type: TokenType): Boolean =
        this == type
}
