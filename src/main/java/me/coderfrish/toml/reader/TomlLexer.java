package me.coderfrish.toml.reader;

import me.coderfrish.toml.reader.token.Token;
import me.coderfrish.toml.reader.token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class TomlLexer {
    private static final String WORD_PATTERN = "[A-Za-z]";
    private static final String NUMBER_PATTERN = "[0-9]";
    private final char[] chars;
    private int offset = 0;

    public TomlLexer(String toml) {
        this.chars = toml.toCharArray();
    }

    public List<Token> tokenize() {
        final List<Token>  tokens = new ArrayList<>();

        while (offset < chars.length) {
            char current = chars[offset];

            if (current == '\n') {
                offset++;
                continue;
            }

            if (matches(current, WORD_PATTERN)) {
                StringBuilder builder = new StringBuilder();
                while (offset < chars.length && matches(chars[offset], WORD_PATTERN)) {
                    builder.append(chars[offset]);
                    offset++;
                }
                tokens.add(new Token(TokenType.WORD, builder));
                continue;
            } else if (matches(current, NUMBER_PATTERN)) {
                StringBuilder builder = new StringBuilder();
                while (offset < chars.length && matches(chars[offset], NUMBER_PATTERN)) {
                    builder.append(chars[offset]);
                    offset++;
                }
                tokens.add(new Token(TokenType.NUMBER, builder));
                continue;
            } else {
                tokens.add(new Token(TokenType.SYMBOL, String.valueOf(current)));
            }

            offset++;
        }

        return tokens;
    }

    private static boolean matches(char c, String pattern) {
        return String.valueOf(c).matches(pattern);
    }
}
