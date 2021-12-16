package letlang.ktlet.tokenizer.partizer.string

import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.PartizerEmitter.Companion.includeIf

object StringPartizerCommon {

    fun matchTripleQuote(emitter: PartizerEmitter): Boolean {
        return (emitter.includeIf { it match '\"' } &&
                emitter.includeIf { it match '\"' } &&
                emitter.includeIf { it match '\"' })
    }

    fun matchEscape(emitter: PartizerEmitter) {
        if (emitter.current match '\\') {
            emitter.include()
            emitter.include()
        }
    }

}
