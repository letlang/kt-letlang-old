package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Token
import letlang.ktlet.unicode.CodePoint

object WhitespacePartizer : BasicPartizer {
    override val token: Token get() = Token.WHITESPACE
    override fun part(cp: CodePoint): Boolean = Character.isWhitespace(cp.value) && cp.value != '\n'.code
}
