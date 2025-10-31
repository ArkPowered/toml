package me.coderfrish.toml;

import java.util.Objects;

public class TomlParser {
    private final char[] chars;
    private int offset = 0;

    public TomlParser(String chars) {
        this.chars = chars.toCharArray();
    }

    public void parse() {
        while (offset < chars.length) {
            char ch = chars[offset];
            if (ch == '"') {
                System.out.println("String | " + readString());
            } else if (ch == '=') {
                System.out.println("Equals | " + ch);
            } else {
                if (Character.isJavaIdentifierStart(ch)) {
                    String identifier = readIdentifier();
                    switch (identifier) {
                        case "false", "true" -> {
                            System.out.println("Bool | " + identifier);
                        }

                        default -> {
                            System.out.println("Identifier | " + identifier);
                        }
                    }
                }
            }

            offset++;
        }
    }

    public String readIdentifier() {
        StringBuilder identifier = new StringBuilder();
        while (Character.isJavaIdentifierPart(chars[offset]) || chars[offset] == '.') {
            identifier.append(chars[offset++]);
        }
        this.offset = this.offset - 1;

        return identifier.toString();
    }

    public String readString() {
        StringBuilder string = new StringBuilder();
        this.offset = this.offset + 1;
        while (chars[offset] != '"') {
            string.append(chars[offset++]);
        }

        return string.toString();
    }
}
