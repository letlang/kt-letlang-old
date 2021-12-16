package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Partizer
import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.PartizerEmitter.Companion.includeIf
import letlang.ktlet.tokenizer.Token

object NestCommentPartizer : Partizer {
    override fun apply(emitter: PartizerEmitter) {
        if (emitter.includeIf { it match '/' } &&
            emitter.includeIf { it match '`' }
        ) {
            var depth = 0
            while (true) {
                if (!emitter.current.isValid) {
                    emitter.emit(Token.BLOCK_COMMENT)
                    emitter.emit(Token.UNEXPECTED)
                    emitter.commit()
                    return
                }
                if (emitter.includeIf { it match '/' } &&
                    emitter.includeIf { it match '`' }
                ) {
                    depth++
                }
                if (emitter.includeIf { it match '`' } &&
                    emitter.includeIf { it match '/' }
                ) {
                    depth--
                    if (depth == 0) {
                        emitter.emit(Token.BLOCK_COMMENT)
                        emitter.commit()
                        return
                    }
                }
                emitter.include()
            }
        }
    }
}
