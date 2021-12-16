package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Partizer
import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.PartizerEmitter.Companion.includeIf
import letlang.ktlet.tokenizer.Token

object LineCommentPartizer : Partizer {
    override fun apply(emitter: PartizerEmitter) {
        if (emitter.includeIf { it match '/' } &&
            emitter.includeIf { it match '/' }) {
            while (true) {
                if (!emitter.current.isValid || emitter.includeIf { it match '\n' }) {
                    emitter.emit(Token.LINE_COMMENT)
                    emitter.commit()
                    return
                }
                emitter.include()
            }
        }
    }
}
