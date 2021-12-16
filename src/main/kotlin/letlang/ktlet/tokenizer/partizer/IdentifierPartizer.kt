package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Token
import letlang.ktlet.unicode.CodePoint

object IdentifierPartizer : BasicPartizer {
    override val token: Token get() = Token.IDENTIFIER
    override fun start(cp: CodePoint): Boolean =
        Character.getType(cp.value) != Character.CONTROL.toInt() &&
                Character.isJavaIdentifierStart(cp.value) &&
                cp noMatch '$'
    override fun part(cp: CodePoint): Boolean =
        Character.getType(cp.value) != Character.CONTROL.toInt() &&
                Character.isJavaIdentifierPart(cp.value) &&
                cp noMatch '$'
}
