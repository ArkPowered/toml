package me.coderfrish.toml.shared

const val QUOTA_NOT_CONSISTENT_EXCEPTION = "Quotation marks must be consistent."

const val IDENTIFIER_PATTERN = "[A-Za-z0-9+_-]"

const val BOOLEAN_PATTERN = "^(true|false)$"

const val DECIMAL_INTEGER_PATTERN = "[+-]?[0-9](_?[0-9])*"

const val HEX_INTEGER_PATTERN = "0x[0-9A-Fa-f](_?[0-9A-Fa-f])*"

const val OCTAL_INTEGER_PATTERN = "0o[0-7](_?[0-7])*"

const val BINARY_INTEGER_PATTERN = "0b[01](_?[01])*"
