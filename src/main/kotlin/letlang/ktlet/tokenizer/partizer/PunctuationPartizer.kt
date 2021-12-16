package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Token
import letlang.ktlet.unicode.CodePoint

object PunctuationPartizer : BasicPartizer {
    override val token: Token get() = Token.PUNCTUATION
    override fun start(cp: CodePoint): Boolean = ",;".contains(cp.value.toChar())
    override fun part(cp: CodePoint): Boolean = false
}
