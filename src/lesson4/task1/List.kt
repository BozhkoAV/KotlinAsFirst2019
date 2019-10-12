@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val abs = v.map { it * it }
    return if (v.isNotEmpty()) sqrt(abs.sum())
    else 0.0
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>) =
    when {
        list.isNotEmpty() -> list.average()
        else -> 0.0
    }

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val a = mean(list)
    list.replaceAll { it - a }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    for (i in p.indices) {
        var el = p[i]
        for (j in 1..i) {
            el *= x
        }
        result += el
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var x = n
    var list = listOf<Int>()
    if (isPrime(x)) {
        list = list + x
    } else {
        while (x > 1) {
            list = list + minDivisor(x)
            x /= minDivisor(x)
        }
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var list = listOf<Int>()
    var x = n
    while (x > 0) {
        list = list + (x % base)
        x /= base
    }
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var list = listOf<Any>()
    var x = n
    while (x > 0) {
        list = if (x % base > 9) {
            list + ('a' + x % base - 10)
        } else {
            list + (x % base)
        }
        x /= base
    }
    list = list.reversed()
    return list.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val list = digits.reversed()
    var x = 1
    var sum = 0
    for (element in list) {
        sum += x * element
        x *= base
    }
    return sum
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var list = str.toList()
    list = list.reversed()
    var x = 1
    var sum = 0
    for (element in list) {
        sum += if ((element >= 'a') && (element <= 'z')) {
            x * (element.toInt() - 87)
        } else {
            x * (element.toInt() - 48)
        }
        x *= base
    }
    return sum
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var x = n
    var list = listOf<Char>()
    for (i in 1..(x / 1000)) {
        list = list + 'M'
    }
    x %= 1000
    if (x / 100 == 9) {
        list = list + 'C' + 'M'
    }
    if ((x / 100 < 9) && (x / 100 >= 5)) {
        list = list + 'D'
        for (i in 6..(x / 100)) {
            list = list + 'C'
        }
    }
    if (x / 100 == 4) {
        list = list + 'C' + 'D'
    }
    if (x / 100 < 4) {
        for (i in 1..(x / 100)) {
            list = list + 'C'
        }
    }
    x %= 100
    if (x / 10 == 9) {
        list = list + 'X' + 'C'
    }
    if ((x / 10 < 9) && (x / 10 >= 5)) {
        list = list + 'L'
        for (i in 6..(x / 10)) {
            list = list + 'X'
        }
    }
    if (x / 10 == 4) {
        list = list + 'X' + 'L'
    }
    if (x / 10 < 4) {
        for (i in 1..(x / 10)) {
            list = list + 'X'
        }
    }
    x %= 10
    if (x == 9) {
        list = list + 'I' + 'X'
    }
    if ((x < 9) && (x >= 5)) {
        list = list + 'V'
        for (i in 6..x) {
            list = list + 'I'
        }
    }
    if (x == 4) {
        list = list + 'I' + 'V'
    }
    if (x < 4) {
        for (i in 1..x) {
            list = list + 'I'
        }
    }
    return list.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var list = listOf<String>()
    when (n / 100000) {
        1 -> list = list + "сто"
        2 -> list = list + "двести"
        3 -> list = list + "триста"
        4 -> list = list + "четыреста"
        5 -> list = list + "пятьсот"
        6 -> list = list + "шестьсот"
        7 -> list = list + "семьсот"
        8 -> list = list + "восемьсот"
        9 -> list = list + "девятьсот"
    }
    when (n % 100000 / 1000) {
        10 -> list = list + "десять тысяч"
        11 -> list = list + "одиннадцать тысяч"
        12 -> list = list + "двенадцать тысяч"
        13 -> list = list + "тринадцать тысяч"
        14 -> list = list + "четырнадцать тысяч"
        15 -> list = list + "пятнадцать тысяч"
        16 -> list = list + "шестнадцать тысяч"
        17 -> list = list + "семнадцать тысяч"
        18 -> list = list + "восемнадцать тысяч"
        19 -> list = list + "девятнадцать тысяч"
    }
    when (n % 100000 / 10000) {
        2 -> list = list + "двадцать"
        3 -> list = list + "тридцать"
        4 -> list = list + "сорок"
        5 -> list = list + "пятьдесят"
        6 -> list = list + "шестьдесят"
        7 -> list = list + "семьдесят"
        8 -> list = list + "восемьдесят"
        9 -> list = list + "девяносто"
    }
    if (((n % 100000 / 10000) != 1) && (n / 1000 > 0)) {
        when (n % 10000 / 1000) {
            0 -> list = list + "тысяч"
            1 -> list = list + "одна тысяча"
            2 -> list = list + "две тысячи"
            3 -> list = list + "три тысячи"
            4 -> list = list + "четыре тысячи"
            5 -> list = list + "пять тысяч"
            6 -> list = list + "шесть тысяч"
            7 -> list = list + "семь тысяч"
            8 -> list = list + "восемь тысяч"
            9 -> list = list + "девять тысяч"
        }
    }
    when (n % 1000 / 100) {
        1 -> list = list + "сто"
        2 -> list = list + "двести"
        3 -> list = list + "триста"
        4 -> list = list + "четыреста"
        5 -> list = list + "пятьсот"
        6 -> list = list + "шестьсот"
        7 -> list = list + "семьсот"
        8 -> list = list + "восемьсот"
        9 -> list = list + "девятьсот"
    }
    when (n % 100) {
        10 -> list = list + "десять"
        11 -> list = list + "одиннадцать"
        12 -> list = list + "двенадцать"
        13 -> list = list + "тринадцать"
        14 -> list = list + "четырнадцать"
        15 -> list = list + "пятнадцать"
        16 -> list = list + "шестнадцать"
        17 -> list = list + "семнадцать"
        18 -> list = list + "восемнадцать"
        19 -> list = list + "девятнадцать"
    }
    when (n % 100 / 10) {
        2 -> list = list + "двадцать"
        3 -> list = list + "тридцать"
        4 -> list = list + "сорок"
        5 -> list = list + "пятьдесят"
        6 -> list = list + "шестьдесят"
        7 -> list = list + "семьдесят"
        8 -> list = list + "восемьдесят"
        9 -> list = list + "девяносто"
    }
    if ((n % 100 / 10) != 1) {
        when (n % 10) {
            1 -> list = list + "один"
            2 -> list = list + "два"
            3 -> list = list + "три"
            4 -> list = list + "четыре"
            5 -> list = list + "пять"
            6 -> list = list + "шесть"
            7 -> list = list + "семь"
            8 -> list = list + "восемь"
            9 -> list = list + "девять"
        }
    }
    return list.joinToString(separator = " ")
}