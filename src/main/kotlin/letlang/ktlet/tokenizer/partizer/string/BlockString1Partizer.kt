package letlang.ktlet.tokenizer.partizer.string

import letlang.ktlet.tokenizer.Enclosure
import letlang.ktlet.tokenizer.Partizer
import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.PartizerEmitter.Companion.includeIf
import letlang.ktlet.tokenizer.Token

object BlockString1Partizer : Partizer {
    override fun apply(emitter: PartizerEmitter) {
        val interpol = emitter.includeIf { it match '$' }
        if (StringPartizerCommon.matchTripleQuote(emitter)) {
            while (true) {
                if (!emitter.current.isValid) {
                    emitter.emit(Token.BLOCK_STRING)
                    emitter.emit(Token.UNEXPECTED)
                    emitter.commit()
                    return
                }
                StringPartizerCommon.matchEscape(emitter)
                if (StringPartizerCommon.matchTripleQuote(emitter)) {
                    emitter.emit(Token.BLOCK_STRING)
                    emitter.commit()
                    return
                }
                if (interpol && emitter.includeIf { it match '{' }) {
                    emitter.pushEnclosure(Enclosure.BLOCK_STRING_INTERPOL)
                    emitter.emit(Token.BLOCK_STRING)
                    emitter.commit()
                    return
                }
                emitter.include()
            }
        }
    }
}
