package me.coderfrish.toml.shared

object MatchUtils {
    fun matches(string: String, regex: String): Boolean {
        return matches(string, regex.toRegex())
    }

    fun matches(char: Char, regex: String): Boolean {
        return matches(char.toString(), regex)
    }

    fun matches(string: String, regex: Regex): Boolean {
        return string.matches(regex)
    }

    fun isRawNumber(string: String): Boolean =
        matches(string, DECIMAL_INTEGER_PATTERN) ||
                matches(string, HEX_INTEGER_PATTERN) ||
                matches(string, OCTAL_INTEGER_PATTERN) ||
                matches(string, BINARY_INTEGER_PATTERN)
}
