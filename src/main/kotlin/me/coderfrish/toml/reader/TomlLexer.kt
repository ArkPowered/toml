package me.coderfrish.toml.reader

class TomlLexer(private val chars: CharArray) {
    companion object {
        private const val FLOAT_PATTERN: String = "^-?(?:\\d+\\.\\d*|\\.\\d+)$"
        private const val INTEGER_PATTERN: String = "^-?\\d+$"
    }

    private val tokens: MutableList<TomlToken> = ArrayList()
    private var offset = 0
    private var line = 1
    private var column = 1

    constructor(str: String) : this(str.toCharArray())

    fun tokenize(): MutableList<TomlToken> {
        try {
            while (offset < chars.size) {
                this.tokenize0()

                column++
                offset++
            }

            return tokens
        } catch (e: Exception) {
            throw RuntimeException("[$column,$line] parse exception.", e)
        }
    }

    private fun tokenize0() {
        when (peek()) {
            '"' -> tokens.add(TomlToken(TokenType.STRING, readString()))
            '\n' -> {
                line++
                column = 1
            }

            '=' -> tokens.add(TomlToken(TokenType.EQUAL, "="))
            else -> this.readIdentifier0(tokens)
        }
    }

    private fun readIdentifier0(tokens: MutableList<TomlToken>) {
        if (peek().isJavaIdentifierStart()) {
            when (val identifier = readIdentifier()) {
                "false", "true" -> tokens.add(
                    TomlToken(TokenType.BOOLEAN, identifier)
                )

                else -> tokens.add(
                    TomlToken(TokenType.IDENTIFIER, identifier)
                )
            }
        } else {
            if (Character.isDigit(peek())) {
                val number = readNumber()

                if (number.matches(FLOAT_PATTERN.toRegex())) {
                    tokens.add(TomlToken(TokenType.FLOAT, number))
                } else if (number.matches(INTEGER_PATTERN.toRegex())) {
                    tokens.add(TomlToken(TokenType.INTEGER, number))
                } else {
                    throw RuntimeException("$number is not a number.")
                }
            }
        }
    }

    fun readIdentifier(): String {
        val builder = StringBuilder()
        while (peek().isJavaIdentifierPart() || peek() == '-') {
            builder.append(chars[offset++])
        }
        this.offset -= 1

        return builder.toString()
    }


    fun readNumber(): String {
        val builder = StringBuilder()
        while (peek().isDigit() || peek() == '.' || peek() == '-') {
            builder.append(chars[offset++])
        }
        this.offset -= 1

        return builder.toString()
    }

    fun readString(): String {
        val builder = StringBuilder()
        this.offset += 1
        while (peek() != '"') {
            builder.append(chars[offset++])
        }
        this.offset += 1

        return builder.toString()
    }

    fun peek(): Char = chars[offset]
}