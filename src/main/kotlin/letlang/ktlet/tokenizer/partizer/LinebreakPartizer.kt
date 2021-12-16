package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Token
import letlang.ktlet.unicode.CodePoint

object LinebreakPartizer : BasicPartizer {
    override val token: Token get() = Token.LINEBREAK
    override fun part(cp: CodePoint): Boolean = cp.value == '\n'.code
}
