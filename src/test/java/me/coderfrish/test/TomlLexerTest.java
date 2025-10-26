package me.coderfrish.test;

import me.coderfrish.toml.reader.TomlLexer;
import me.coderfrish.toml.reader.TomlParser;
import me.coderfrish.toml.reader.node.Node;

public class TomlLexerTest {
    public static void main(String[] args) {
        String toml =
                """
                name="Frish2021"
                test="Hello"
                message="A Student"
                """;

        TomlLexer lexer = new TomlLexer(toml);
        TomlParser parser = new TomlParser(lexer);

        /*
          age=15
         */
        for (Node node : parser.parse()) {
            System.out.println(node);
        }
    }
}
