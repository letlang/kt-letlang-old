package letlang.ktlet.tokenizer

import org.junit.Assert
import org.junit.Test

class TokenizerTest {

    private fun apply(input: String, vararg expected: Pair<Token, String>) {
        val text = input.replace("\r\n", "\n").replace("\r", "\n")
        val builder = mutableListOf<Pair<Token, String>>()
        TokenizerBuilder()
            .config()
            .input(text)
            .output { { builder.add(it.token to it.text.joinToString("")) } }
            .run()
        Assert.assertEquals(expected.toList(), builder.toList())
    }

    @Test
    fun `Hello World`() = apply(
        "Hello World",
        Token.IDENTIFIER to "Hello",
        Token.WHITESPACE to " ",
        Token.IDENTIFIER to "World"
    )

    @Test
    fun `fun Main`() = apply(
        """fun Main(let Env :Environment) :Unit = {
           |    Env.IO.WriteLine("Hello World")
           |}
        """.trimMargin(),
        Token.IDENTIFIER to "fun",
        Token.WHITESPACE to " ",
        Token.IDENTIFIER to "Main",
        Token.PUNCTUATION to "(",
        Token.IDENTIFIER to "let",
        Token.WHITESPACE to " ",
        Token.IDENTIFIER to "Env",
        Token.WHITESPACE to " ",
        Token.OPERATOR to ":",
        Token.IDENTIFIER to "Environment",
        Token.PUNCTUATION to ")",
        Token.WHITESPACE to " ",
        Token.OPERATOR to ":",
        Token.IDENTIFIER to "Unit",
        Token.WHITESPACE to " ",
        Token.OPERATOR to "=",
        Token.WHITESPACE to " ",
        Token.PUNCTUATION to "{",
        Token.LINEBREAK to "\n",
        Token.WHITESPACE to "    ",
        Token.IDENTIFIER to "Env",
        Token.OPERATOR to ".",
        Token.IDENTIFIER to "IO",
        Token.OPERATOR to ".",
        Token.IDENTIFIER to "WriteLine",
        Token.PUNCTUATION to "(",
        Token.LINE_STRING to "\"Hello World\"",
        Token.PUNCTUATION to ")",
        Token.LINEBREAK to "\n",
        Token.PUNCTUATION to "}",
    )

    @Test
    fun `interpolated string`() = apply(
        "WriteLine($\"Hello {Name}\");",
        Token.IDENTIFIER to "WriteLine",
        Token.PUNCTUATION to "(",
        Token.LINE_STRING to "$\"Hello {",
        Token.IDENTIFIER to "Name",
        Token.LINE_STRING to "}\"",
        Token.PUNCTUATION to ")",
        Token.PUNCTUATION to ";",
    )

}