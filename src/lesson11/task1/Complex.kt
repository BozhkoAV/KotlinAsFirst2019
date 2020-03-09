@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr

/**
 * Класс "комплексое число".
 *
 * Общая сложность задания -- лёгкая.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(s.filter { it != 'i' }.split(Regex("""\b[-|+]"""))[0].toDouble(),
        s.filter { it != 'i' }.split(Regex("""\b[-|+]"""))[1].toDouble()
                * if (s.contains(Regex("""\b[-]"""))) -1 else 1)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex) = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus() = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex) = plus(other.unaryMinus())

    /**
     * Умножение
     */
    operator fun times(other: Complex) = Complex(re * other.re - im * other.im, im * other.re + re * other.im)

    /**
     * Деление
     */
    operator fun div(other: Complex) = Complex(
        (re * other.re + im * other.im) / (sqr(other.re) + sqr(other.im)),
        (im * other.re - re * other.im) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?) =
        when {
            this === other -> true
            other is Complex -> re == other.re && im == other.im
            else -> false
        }

    override fun hashCode(): Int {
        var result = re
        result = 31 * result + im
        return result.toInt()
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String = "$re" + if (im > 0.0) "+${im}i" else if (im == 0.0) "" else "${im}i"
}