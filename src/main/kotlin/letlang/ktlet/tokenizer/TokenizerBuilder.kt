package letlang.ktlet.tokenizer

import letlang.ktlet.unicode.CodePoint

data class TokenizerBuilder(
    var config: List<Partizer>? = null,
    var input: (() -> () -> CodePoint)? = null,
    var output: (() -> (TokenData) -> Unit)? = null,
) {

    fun run() {
        this.build().run()
    }

    fun build(): TokenizerSession {
        return TokenizerSession(this.config!!, this.input!!(), this.output!!())
    }

    fun config(config: List<Partizer> = Partizer.DEFAULT): TokenizerBuilder {
        this.config = config
        return this
    }

    fun input(input: () -> () -> CodePoint): TokenizerBuilder {
        this.input = input
        return this
    }

    fun input(input: Sequence<CodePoint>): TokenizerBuilder {
        this.input = {
            val iterator = input.iterator()
            iterator::next
        }
        return this
    }

    fun input(input: String): TokenizerBuilder {
        this.input = {
            val iterator = input.codePoints().iterator();
            { CodePoint(iterator.next()) }
        }
        return this
    }

    fun output(output: () -> (TokenData) -> Unit): TokenizerBuilder {
        this.output = output
        return this
    }

}