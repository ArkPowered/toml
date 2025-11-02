package me.coderfrish.toml.api

/**
 * @author Frish2021
 * @see me.coderfrish.toml.base.BaseParser
 */
interface Parser {
    /**
     * @author Frish2021
     * @return Toml Map
     */
    fun parse(): Map<String, Any>
}
