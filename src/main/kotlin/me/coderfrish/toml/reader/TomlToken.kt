package me.coderfrish.toml.reader

data class TomlToken(val type: TokenType, val value: String) {
    companion object {
        fun stringOf(value: String): TomlToken {
            return TomlToken(TokenType.STRING, value)
        }

        fun identifierOf(value: String): TomlToken {
            return TomlToken(TokenType.IDENTIFIER, value)
        }

        fun equalsOf(value: Char): TomlToken {
            return TomlToken(TokenType.EQUALS, value.toString())
        }
    }
}
