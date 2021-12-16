package letlang.ktlet.tokenizer

enum class Token {
    UNEXPECTED,
    WHITESPACE,
    LINEBREAK,
    IDENTIFIER,
    PUNCTUATION,
    OPERATOR,
    LINE_COMMENT,
    BLOCK_COMMENT,
    NEST_COMMENT,
    LINE_STRING,
    BLOCK_STRING,
}
