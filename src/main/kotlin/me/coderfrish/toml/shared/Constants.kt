package me.coderfrish.toml.shared

const val QUOTA_NOT_CONSISTENT_EXCEPTION = "Quotation marks must be consistent."

const val IDENTIFIER_PATTERN = "[A-Za-z0-9+_-]"

const val BOOLEAN_PATTERN = "^(true|false)$"

/* Number Section */
const val NOT_A_NUMBER_PATTERN = "^(?i)(nan|\\-nan|\\+nan)$"

const val INFINITE_PATTERN = "^(?i)(inf|\\-inf|\\+inf)$"

const val DECIMAL_INTEGER_PATTERN = "^(?i)[-+]?(?:0|[1-9](?:_?[0-9])*)$"

const val HEX_INTEGER_PATTERN = "^(?i)0[xX][0-9a-fA-F](?:_?[0-9a-fA-F])*$"

const val OCTAL_INTEGER_PATTERN = "^(?i)0[oO][0-7](?:_?[0-7])*$"

const val BINARY_INTEGER_PATTERN = "^(?i)0[bB][01](?:_?[01])*$"
