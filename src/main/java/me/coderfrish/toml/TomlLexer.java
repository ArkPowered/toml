package me.coderfrish.toml;

import java.util.ArrayList;
import java.util.List;

public class TomlLexer {
    private static final String FLOAT_PATTERN = "^-?(?:\\d+\\.\\d*|\\.\\d+)$";
    private static final String INTEGER_PATTERN = "^-?\\d+$";
    private final List<TomlToken> tokens = new ArrayList<>();
    private final char[] chars;
    private int offset = 0;
    private int line = 1;
    private int column = 1;

    public TomlLexer(String chars) {
        this.chars = chars.toCharArray();
    }

    public List<TomlToken> tokenize() {
        try {
            while (offset < chars.length) {
                this.tokenize0();

                column++;
                offset++;
            }

            return tokens;
        } catch (Exception e) {
            throw new RuntimeException("[" + column + "," + line + "] exception.");
        }
    }

    private void tokenize0() {
        switch (peek()) {
            case '"' -> tokens.add(new TomlToken(TokenType.STRING, readString()));

            case '\n' -> {
                line++;
                column = 1;
            }

            case '=' -> tokens.add(new TomlToken(TokenType.EQUAL, "="));

            default -> this.readIdentifier0(tokens);
        }
    }

    private void readIdentifier0(List<TomlToken> tokens) {
        if (isIdentifierStart()) {
            String identifier = readIdentifier();
            switch (identifier) {
                case "false", "true" -> tokens.add(
                        new TomlToken(TokenType.BOOLEAN, identifier)
                );

                default -> tokens.add(
                        new TomlToken(TokenType.IDENTIFIER, identifier)
                );
            }
        } else {
            if (Character.isDigit(peek())) {
                String number = readNumber();

                if (number.matches(FLOAT_PATTERN)) {
                    tokens.add(new TomlToken(TokenType.FLOAT, number));
                } else if (number.matches(INTEGER_PATTERN)) {
                    tokens.add(new TomlToken(TokenType.INTEGER, number));
                } else {
                    throw new RuntimeException(number + " is not a number.");
                }
            }
        }
    }

    public boolean isIdentifierStart() {
        return Character.isJavaIdentifierStart(peek());
    }

    public boolean isIdentifierPart() {
        return Character.isJavaIdentifierPart(peek());
    }

    public String readIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (isIdentifierPart() || peek() == '-') {
            builder.append(chars[offset++]);
        }
        this.offset = this.offset - 1;

        return builder.toString();
    }


    public String readNumber() {
        StringBuilder builder = new StringBuilder();
        while (Character.isDigit(peek()) || peek() == '.' || peek() == '-') {
            builder.append(chars[offset++]);
        }
        this.offset = this.offset - 1;

        return builder.toString();
    }

    public String readString() {
        StringBuilder builder = new StringBuilder();
        this.offset = this.offset + 1;
        while (peek() != '"') {
            builder.append(chars[offset++]);
        }
        this.offset = this.offset + 1;

        return builder.toString();
    }

    public char peek() {
        return chars[offset];
    }
}
