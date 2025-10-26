package me.coderfrish.toml.reader.token;

import org.jetbrains.annotations.NotNull;

public record Token(TokenType type, String value) {
    public Token(TokenType type, StringBuilder builder) {
        this(type, builder.toString());
    }

    @Override
    public @NotNull String toString() {
        return type + ":" + value;
    }
}
