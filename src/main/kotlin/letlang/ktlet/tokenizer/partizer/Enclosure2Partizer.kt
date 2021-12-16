package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Enclosure
import letlang.ktlet.tokenizer.Partizer
import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.Token

object Enclosure2Partizer : Partizer {
    override fun apply(emitter: PartizerEmitter) {
        if ((emitter.current match ')' && emitter.enclosure == Enclosure.PARENTHESES) ||
            (emitter.current match ']' && emitter.enclosure == Enclosure.BRACES) ||
            (emitter.current match '}' && emitter.enclosure == Enclosure.BRACKETS)
        ) {
            emitter.include()
            emitter.popEnclosure()
            emitter.emit(Token.PUNCTUATION)
            emitter.commit()
        }
    }
}
