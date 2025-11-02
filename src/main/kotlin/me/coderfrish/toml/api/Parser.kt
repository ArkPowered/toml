package me.coderfrish.toml.api

interface Parser {
    fun parse(): Map<String, Any>
}
