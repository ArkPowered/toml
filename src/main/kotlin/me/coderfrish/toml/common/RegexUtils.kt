package me.coderfrish.toml.common

object RegexUtils {
    private val IDENTIFIER_PATTERN = me.coderfrish.toml.common.IDENTIFIER_PATTERN.toRegex()

    fun isIdentifierPart(char: Char): Boolean {
        return char.toString().matches(IDENTIFIER_PATTERN)
    }
}
