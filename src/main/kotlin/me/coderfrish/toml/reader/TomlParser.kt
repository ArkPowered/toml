package me.coderfrish.toml.reader

class TomlParser(private val lexer: TomlLexer) {
    private val entries: MutableMap<String, Any> = LinkedHashMap()
    private val tokens: MutableList<TomlToken> = lexer.tokenize()
    private var offset = 0

    fun parse(): MutableMap<String, Any> {
        while (this.offset < this.tokens.size) {
            val token = this.tokens[this.offset]
            if (token.type == TokenType.IDENTIFIER) {
                val equals = this.tokens[this.offset + 1]

                if (equals.type == TokenType.EQUAL) {
                    this.offset += 1
                    val value = this.tokens[this.offset + 1]

                    if (value.type != TokenType.IDENTIFIER) {
                        when (value.type) {
                            TokenType.STRING ->
                                entries[token.value] = parseString(value.value)

                            TokenType.INTEGER ->
                                entries[token.value] = parseInteger(value.value)

                            TokenType.FLOAT ->
                                entries[token.value] = parseFloat(value.value)

                            TokenType.BOOLEAN ->
                                entries[token.value] = parseBoolean(value.value)

                            else ->
                                throw RuntimeException(token.type.toString() + " is not a value.")
                        }
                    } else {
                        throw RuntimeException("Value cannot be identifier.")
                    }
                }
            }

            this.offset++
        }

        return this.entries
    }

    fun parseString(value: String): Any {
        return value
    }

    fun parseInteger(value: String): Any {
        return value.toInt()
    }

    fun parseFloat(value: String): Any {
        return value.toFloat()
    }

    fun parseBoolean(value: String): Boolean {
        return value.toBoolean()
    }
}