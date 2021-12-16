package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Enclosure
import letlang.ktlet.tokenizer.Partizer
import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.PartizerEmitter.Companion.includeIf
import letlang.ktlet.tokenizer.Token

object Enclosure1Partizer : Partizer {
    override fun apply(emitter: PartizerEmitter) {
        if (emitter.includeIf { it match '(' }) {
            emitter.pushEnclosure(Enclosure.PARENTHESES)
            emitter.emit(Token.PUNCTUATION)
            emitter.commit()
        }
        if (emitter.includeIf { it match '[' }) {
            emitter.pushEnclosure(Enclosure.BRACES)
            emitter.emit(Token.PUNCTUATION)
            emitter.commit()
        }
        if (emitter.includeIf { it match '{' }) {
            emitter.pushEnclosure(Enclosure.BRACKETS)
            emitter.emit(Token.PUNCTUATION)
            emitter.commit()
        }
    }
}
