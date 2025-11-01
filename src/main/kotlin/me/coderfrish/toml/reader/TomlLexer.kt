package me.coderfrish.toml.reader

class TomlLexer(private val chars: CharArray) {
    constructor(str: String): this(str.toCharArray())

    companion object {
        private const val NORMAL_IDENTIFIER_PATTERN = "[A-Za-z0-9_-]"
    }

    private val tokens = mutableListOf<TomlToken>()
    private var position = 0

    private fun peek(): Char = chars[position]

    private fun readString(): String {
        val builder = StringBuilder()
        position++
        while (peek() != '"') {
            builder.append(chars[position++])
        }
        position++

        return builder.toString()
    }

    private fun readIdentifier(): String {
        val builder = StringBuilder()
        while (isIdentifierPart(chars[this.position]) || chars[this.position] == '.') {
            builder.append(chars[this.position++])
        }

        return builder.toString()
    }

    internal fun tokenize(): List<TomlToken> {
        while (this.position < chars.size) {
            when (chars[this.position]) {
                '=' -> {
                    tokens.add(TomlToken.equalsOf(
                        chars[this.position++]
                    ))
                }

                '\n', ' ' -> {
                    position++
                }

                '"' -> {
                    tokens.add(TomlToken.stringOf(
                        readString()
                    ))
                }

                else -> {
                    tokens.add(TomlToken.identifierOf(
                        readIdentifier()
                    ))
                }
            }
        }

        return this.tokens
    }

    private fun isIdentifierPart(char: Char): Boolean {
        return char.toString().matches(NORMAL_IDENTIFIER_PATTERN.toRegex())
    }
}
