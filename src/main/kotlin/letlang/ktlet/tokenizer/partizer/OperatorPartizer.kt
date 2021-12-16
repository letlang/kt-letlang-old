package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Token
import letlang.ktlet.unicode.CodePoint

object OperatorPartizer : BasicPartizer {
    override val token: Token get() = Token.OPERATOR
    override fun part(cp: CodePoint): Boolean =
        "<>=+-*/\\%&|^~!?#@`$.:".contains(cp.value.toChar())
}
