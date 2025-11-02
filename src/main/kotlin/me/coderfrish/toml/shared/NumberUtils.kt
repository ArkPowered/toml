package me.coderfrish.toml.shared

import me.coderfrish.toml.shared.MatchUtils.matches

object NumberUtils {
    fun toNumber(string: String): Number {
        return if (isDecimal(string)) {
            decimal(string)
        } else if (isHex(string)) {
            hex(string)
        } else if (isOctal(string)) {
            octal(string)
        } else if (isBinary(string)) {
            binary(string)
        } else if (isNotANumber(string)) {
            nan(string)
        } else if (isInfinite(string)) {
            infinite(string)
        }else {
            error("Invalid number format: $string")
        }
    }

    private fun isNotANumber(string: String): Boolean =
        matches(string, NOT_A_NUMBER_PATTERN)

    private fun isInfinite(string: String): Boolean =
        matches(string, INFINITE_PATTERN)

    private fun isDecimal(string: String): Boolean =
        matches(string, DECIMAL_INTEGER_PATTERN)

    private fun isHex(string: String): Boolean =
        matches(string, HEX_INTEGER_PATTERN)

    private fun isBinary(string: String): Boolean =
        matches(string, BINARY_INTEGER_PATTERN)

    private fun isOctal(string: String): Boolean =
        matches(string, OCTAL_INTEGER_PATTERN)

    fun infinite(string: String): Double =
        if (string.startsWith("-"))
            Double.NEGATIVE_INFINITY
        else
            Double.POSITIVE_INFINITY

    fun nan(string: String): Double =
        if (string.startsWith("-"))
            -Double.NaN
        else
            Double.NaN

    fun decimal(string: String): Long =
        string.replace("_", "").toLong()

    fun hex(string: String): Long =
        string.removePrefix("0x").removePrefix("0X").toLong(16)

    fun octal(string: String): Long =
        string.removePrefix("0o").removePrefix("0O").toLong(8)

    fun binary(string: String): Long =
        string.removePrefix("0b").removePrefix("0B").toLong(2)
}
