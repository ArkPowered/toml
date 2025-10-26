package me.coderfrish.toml.reader;

public class TomlReader {
    private final TomlLexer lexer;

    public TomlReader(String toml) {
        this.lexer = new TomlLexer(toml);
    }
}
