package letlang.ktlet.tokenizer.partizer

import letlang.ktlet.tokenizer.Partizer
import letlang.ktlet.tokenizer.PartizerEmitter
import letlang.ktlet.tokenizer.Token
import letlang.ktlet.unicode.CodePoint

interface BasicPartizer : Partizer {

    val token: Token

    fun part(cp: CodePoint): Boolean

    fun start(cp: CodePoint): Boolean = this.part(cp)

    override fun apply(emitter: PartizerEmitter) {
        if (this.start(emitter.current)) {
            emitter.include()
            while (part(emitter.current)) {
                emitter.include()
            }
            emitter.emit(this.token)
            emitter.commit()
        }
    }

}
