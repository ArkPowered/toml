package me.coderfrish.toml.reader

import kotlin.text.toBoolean

class TomlParser(lexer: TomlLexer) {
    private val entries = mutableMapOf<String, Any>()
    private val tokens = lexer.tokenize()
    private var position = 0

    private fun peek(): TomlToken = tokens[position]

    fun parse(): Map<String, Any> {
        while (position < tokens.size) {
            val key = peek()
            when (key.type) {
                TokenType.IDENTIFIER -> {
                    this.position++
                    parseValue(key)
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

        return entries
    }

    private fun parseValue(key: TomlToken) {
        if (peek().type == TokenType.EQUALS) {
            this.position++
            val value = peek()

            when (value.type) {
                TokenType.IDENTIFIER -> {
                    when (value.value) {
                        "false", "true" -> entries[key.value] = value.value.toBoolean()
                    }
                }

                TokenType.STRING -> entries[key.value] = value.value

                else -> {
                    throw RuntimeException("Value cannot be: ${peek().type}")
                }
            }
        } else {
            throw RuntimeException("Identifier must has equals.")
        }
    }
}
