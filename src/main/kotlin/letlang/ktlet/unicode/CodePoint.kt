package letlang.ktlet.unicode

@JvmInline
value class CodePoint(val value: Int) {

    val isValid: Boolean get() = this.value >= 0

    infix fun match(char: Char): Boolean = this.value == char.code

    infix fun noMatch(char: Char): Boolean = this.value != char.code

    override fun toString(): String =
        Character.toString(this.value)

}
