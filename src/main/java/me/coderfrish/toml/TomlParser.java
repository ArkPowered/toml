package me.coderfrish.toml;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TomlParser {
    private final Map<String, Object> entries = new LinkedHashMap<>();
    private final List<TomlToken> tokens;
    private int offset = 0;

    public TomlParser(TomlLexer lexer) {
        this.tokens = lexer.tokenize();
    }

    /* 目前好像是这样写的 */
    public Map<String, Object> parse() {
        while (this.offset < this.tokens.size()) {
            TomlToken token = this.tokens.get(this.offset);
            if (token.type() == TokenType.IDENTIFIER) {
                TomlToken equals = this.tokens.get(this.offset + 1);

                if (equals.type() == TokenType.EQUAL) {
                    this.offset = this.offset + 1;
                    TomlToken value = this.tokens.get(this.offset + 1);

                    if (value.type() != TokenType.IDENTIFIER) {
                        switch (value.type()) {
                            case STRING ->
                                    entries.put(token.value(), parseString(value.value()));

                            case INTEGER ->
                                entries.put(token.value(), parseInteger(value.value()));

                            case FLOAT ->
                                entries.put(token.value(), parseFloat(value.value()));

                            case BOOLEAN ->
                                entries.put(token.value(), parseBoolean(value.value()));

                            default ->
                                throw new RuntimeException(token.type() + " is not a value.");
                        }
                    } else {
                        throw new RuntimeException("Value cannot be identifier.");
                    }
                }
            }

            this.offset++;
        }

        return this.entries;
    }

    public Object parseString(String value) {
        return value;
    }

    public Object parseInteger(String value) {
        return Integer.parseInt(value);
    }

    public Object parseFloat(String value) {
        return Float.parseFloat(value);
    }

    public boolean parseBoolean(String value) {
        return Boolean.parseBoolean(value);
    }
}
