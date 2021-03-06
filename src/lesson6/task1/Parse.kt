@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ").toMutableList()
    if (parts.size > 3) return ""
    try {
        when (parts[1]) {
            "января" -> parts[1] = "1"
            "февраля" -> parts[1] = "2"
            "марта" -> parts[1] = "3"
            "апреля" -> parts[1] = "4"
            "мая" -> parts[1] = "5"
            "июня" -> parts[1] = "6"
            "июля" -> parts[1] = "7"
            "августа" -> parts[1] = "8"
            "сентября" -> parts[1] = "9"
            "октября" -> parts[1] = "10"
            "ноября" -> parts[1] = "11"
            "декабря" -> parts[1] = "12"
            else -> return ""
        }
        if ((parts[0].toInt() <= daysInMonth(parts[1].toInt(), parts[2].toInt()))
            && (parts[0].toInt() > 0) && (parts[2].toInt() >= 0)
        ) {
            return String.format("%02d.%02d.%s", parts[0].toInt(), parts[1].toInt(), parts[2])
        }
    } catch (e: IndexOutOfBoundsException) {
        return ""
    }
    return ""
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".").toMutableList()
    println(parts)
    if (parts.size > 3) return ""
    try {
        if ((parts[0].toInt() <= daysInMonth(parts[1].toInt(), parts[2].toInt()))
            && (parts[0].toInt() > 0) && (parts[2].toInt() >= 0)
        ) {
            when (parts[1].toInt()) {
                1 -> parts[1] = "января"
                2 -> parts[1] = "февраля"
                3 -> parts[1] = "марта"
                4 -> parts[1] = "апреля"
                5 -> parts[1] = "мая"
                6 -> parts[1] = "июня"
                7 -> parts[1] = "июля"
                8 -> parts[1] = "августа"
                9 -> parts[1] = "сентября"
                10 -> parts[1] = "октября"
                11 -> parts[1] = "ноября"
                12 -> parts[1] = "декабря"
                else -> return ""
            }
            return String.format("%d %s %s", parts[0].toInt(), parts[1], parts[2])
        }
    } catch (e: IndexOutOfBoundsException) {
        return ""
    } catch (e: NumberFormatException) {
        return ""
    }
    return ""
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    try {
        for (i in 0..phone.length - 2) {
            if (phone[i].toString() + phone[i + 1] == "()") return ""
        }
        val phone2 = phone.split(' ', '-', '_', '(', ')')
        var phone3 = ""
        for (element in phone2) {
            phone3 += element
        }
        var check = ""
        if (phone3.first() == '+') check = "+"
        if (phone3.filter { it !in '0'..'9' } == check) return phone3
    } catch (e: NoSuchElementException) {
        return ""
    }
    return ""
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val jumps2 = jumps.split(" ").toMutableList().filter { it != "%" && it != "-" }
    return try {
        jumps2.map { it.toInt() }.max() ?: -1
    } catch (e: NumberFormatException) {
        -1
    }
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val jumps2 = jumps.split(" ").toMutableList()
    val jumps3 = mutableMapOf<Int, String>()
    for (i in 0..jumps2.size - 2 step 2) {
        jumps3[jumps2[i].toInt()] = jumps2[i + 1]
    }
    return jumps3.filterValues { it == "+" }.keys.max() ?: -1
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    require(expression != "")
    val expression2 = expression.split(" ").toMutableList()
    for (char in expression2[0]) {
        require(char in '0'..'9')
    }
    var result = expression2[0].toInt()
    for (i in 0 until expression2.size step 2) {
        for (char in expression2[i]) {
            require(char in '0'..'9' && char.toInt() >= 0)
        }
    }
    for (i in 1..expression2.size - 2 step 2) {
        when {
            expression2[i] == "+" -> result += expression2[i + 1].toInt()
            expression2[i] == "-" -> result -= expression2[i + 1].toInt()
            else -> throw IllegalArgumentException()
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val str2 = str.split(" ").map { it.toLowerCase() }
    val map = mutableMapOf<Int, String>()
    var result = 0
    for (element in str2) {
        map[result] = element
        result += element.length + 1
    }
    for ((key, value) in map) {
        if (map[key] == map[key + value.length + 1]) return key
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val description2 = description.split(' ', ';').filter { it != "" }
    print(description2)
    val map = mutableMapOf<String, Double>()
    try {
        for (i in description2.indices step 2) {
            map[description2[i]] = description2[i + 1].toDouble()
        }
    } catch (e: NumberFormatException) {
        return ""
    }
    for ((key, value) in map) {
        if (value == map.values.max()) return key
    }
    return ""
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()

fun money(sum: Double, coins: String): List<Pair<Int, Double>> {
    require(sum >= 0)
    val list = coins.split(", ")
    for (element in list) {
        require(element.matches(Regex("""[0-9]+.[0-9]+""")) || element.matches(Regex("""[0-9]+""")))
    }
    val listDouble = list.map { it.toDouble() }
    var result = sum
    val finalList = mutableListOf<Pair<Int, Double>>()
    for (i in listDouble.indices) {
        var count = 0
        while (result >= listDouble[i]) {
            result -= listDouble[i]
            count++
        }
        if (count > 0) {
            finalList += count to listDouble[i]
        }
    }
    return finalList
}