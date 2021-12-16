package letlang.ktlet.tokenizer

import letlang.ktlet.unicode.CodePoint

interface PartizerEmitter {

//
//  Character
//

    val current: CodePoint

    fun include()

//
//  Enclosure
//

    val enclosure: Enclosure?

    fun pushEnclosure(enclosure: Enclosure)

    fun popEnclosure()

//
//  Tokens
//

    fun emit(token: Token, data: Any? = null)

    fun commit()

    fun revoke()

//
//  Extensions
//

    companion object{
        inline fun PartizerEmitter.includeIf(body: (CodePoint) -> Boolean): Boolean {
            val result = body(this.current)
            if (result) this.include()
            return result
        }
    }

}
