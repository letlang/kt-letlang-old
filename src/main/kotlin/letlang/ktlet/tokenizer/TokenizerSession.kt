package letlang.ktlet.tokenizer

import letlang.ktlet.unicode.CodePoint

class TokenizerSession constructor(
    private val config: List<Partizer> = Partizer.DEFAULT,
    private val input: () -> CodePoint,
    private val output: (TokenData) -> Unit
) : PartizerEmitter {

    private val buf = mutableListOf<CodePoint>()
    private val tok = mutableListOf<TokenData>()
    private val enc = mutableListOf<Enclosure>()
    private var lat = 0
    private var inc = 0
    private var end = false
    private var com = false

    fun run() {
        do {
            this.step()
            this.com = false
        }
        while (!this.end)
    }

    private fun step() {
        for (partizer in this.config) {
            partizer.apply(this)
            this.revoke()
            if (this.buf.isEmpty() || this.end || this.com) {
                return
            }
        }
        this.emit(Token.UNEXPECTED)
        this.commit()
        this.end = true
    }

    override val current: CodePoint
        get() {
            if (!this.end) {
                if (this.inc < this.buf.size) {
                    return this.buf[this.inc]
                }
                try {
                    val i = this.input()
                    if (i.isValid) {
                        this.buf.add(i)
                        return i
                    }
                } catch (_: NoSuchElementException) {
                }
            }
            this.end = true
            return CodePoint(-1)
        }

    override val enclosure: Enclosure?
        get() = this.enc.lastOrNull()

    override fun include() {
        this.inc++
    }

    override fun pushEnclosure(enclosure: Enclosure) {
        this.enc.add(enclosure)
    }

    override fun popEnclosure() {
        this.enc.removeLast()
    }

    override fun emit(token: Token, data: Any?) {
        this.tok.add(TokenData(token, this.enc.toList(), this.buf.take(this.inc).toList(), this.lat, data))
        if (!this.end) {
            repeat(this.inc) {
                this.buf.removeFirst()
            }
            this.lat += this.inc
        }
    }

    override fun commit() {
        this.tok.forEach(this.output)
        this.tok.clear()
        this.com = true
    }

    override fun revoke() {
        this.inc = 0
        this.tok.clear()
    }

}