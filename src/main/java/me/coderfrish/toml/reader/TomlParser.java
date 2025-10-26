package me.coderfrish.toml.reader;

import me.coderfrish.toml.reader.node.Node;
import me.coderfrish.toml.reader.token.Token;

import java.util.List;

public class TomlParser {
    private final List<Token> tokens;
    private int offset = 0;

    public TomlParser(TomlLexer lexer) {
        this.tokens = lexer.tokenize();
    }

    public List<Node> parse() {
        return List.of();
    }
}
