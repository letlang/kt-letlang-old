package letlang.ktlet.tokenizer.partizer.string

import letlang.ktlet.tokenizer.Enclosure
import letlang.ktlet.tokenizer.Partizer
import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.PartizerEmitter.Companion.includeIf
import letlang.ktlet.tokenizer.Token

object LineString1Partizer : Partizer {
    override fun apply(emitter: PartizerEmitter) {
        val interpol = emitter.includeIf { it match '$' }
        if (emitter.includeIf { it match '\"' }) {
            while (true) {
                if (!emitter.current.isValid) {
                    emitter.emit(Token.LINE_STRING)
                    emitter.emit(Token.UNEXPECTED)
                    emitter.commit()
                    return
                }
                StringPartizerCommon.matchEscape(emitter)
                if (emitter.includeIf { it match '\"' }) {
                    emitter.emit(Token.LINE_STRING)
                    emitter.commit()
                    return
                }
                if (interpol && emitter.includeIf { it match '{' }) {
                    emitter.pushEnclosure(Enclosure.LINE_STRING_INTERPOL)
                    emitter.emit(Token.LINE_STRING)
                    emitter.commit()
                    return
                }
                if (emitter.current match '\n') {
                    emitter.emit(Token.BLOCK_STRING)
                    emitter.emit(Token.UNEXPECTED)
                    emitter.commit()
                    return
                }
                emitter.include()
            }
        }
    }
}
