package me.coderfrish.toml.reader.node;

import org.jetbrains.annotations.NotNull;

public record Node(String key, String value) {
    @Override
    public @NotNull String toString() {
        return key + "=" + value;
    }
}
