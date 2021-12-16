package letlang.ktlet.tokenizer

import letlang.ktlet.tokenizer.partizer.*
import letlang.ktlet.tokenizer.partizer.string.BlockString1Partizer
import letlang.ktlet.tokenizer.partizer.string.BlockString2Partizer
import letlang.ktlet.tokenizer.partizer.string.LineString1Partizer
import letlang.ktlet.tokenizer.partizer.string.LineString2Partizer

interface Partizer {

    fun apply(emitter: PartizerEmitter)

    companion object {
        val DEFAULT = listOf(
            LinebreakPartizer,
            WhitespacePartizer,
            IdentifierPartizer,
            BlockString1Partizer,
            BlockString2Partizer,
            LineString1Partizer,
            LineString2Partizer,
            LineCommentPartizer,
            BlockCommentPartizer,
            NestCommentPartizer,
            Enclosure1Partizer,
            Enclosure2Partizer,
            OperatorPartizer,
            PunctuationPartizer,
        )
    }

}
