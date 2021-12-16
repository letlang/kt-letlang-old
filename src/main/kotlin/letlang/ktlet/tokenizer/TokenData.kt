package letlang.ktlet.tokenizer

import letlang.ktlet.unicode.CodePoint

data class TokenData(
    val token: Token,
    val enclosure: List<Enclosure>,
    val text: List<CodePoint>,
    val offset: Int,
    val data: Any? = null
)
