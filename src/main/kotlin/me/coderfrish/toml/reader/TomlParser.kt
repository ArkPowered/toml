package me.coderfrish.toml.reader

class TomlParser(private val lexer: TomlLexer) {
    private val tokens = lexer.tokenize()

    private var position = 0

    private fun peek(): TomlToken = tokens[position]

    fun parse() {
        while (position < tokens.size) {
            val key = peek()
            when (key.type) {
                TokenType.IDENTIFIER -> {
                    this.position++
                    if (peek().type == TokenType.EQUALS) {
                        this.position++
                        val value = peek()

                        println("${key.value} = ${value.value}")
                    } else {
                        throw RuntimeException("Identifier must has equals.")
                    }

                    this.position++
                }

                TokenType.EOF -> {
                    break
                }

                else -> {
                    throw RuntimeException("Unknown token type: ${peek().type}")
                }
            }
        }
    }
}
