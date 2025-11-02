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
}
