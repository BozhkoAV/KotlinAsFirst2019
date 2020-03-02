@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {

    private val names: MutableSet<Human> = mutableSetOf()

    class Human(val humanName: String, val numbers: MutableSet<String>)

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun addHuman(name: String): Boolean {
        return if (names.find { it.humanName == name } == null) {
            val add = Human(name, mutableSetOf())
            names += add
            true
        } else {
            false
        }
    }

    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun removeHuman(name: String): Boolean {
        return if (names.find { it.humanName == name } != null) {
            val remove = Human(name, mutableSetOf())
            names -= remove
            true
        } else {
            false
        }
    }

    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */
    fun addPhone(name: String, phone: String): Boolean {
        if (names.find { it.humanName == name } != null) {
            if (names.filter { it.humanName == name }.find { phone in it.numbers } == null) {
                if (names.all { phone !in it.numbers }) {
                    names.filter { it.humanName == name }.map { it.numbers += phone }
                    return true
                }
                return false
            }
            return false
        }
        return false
    }

    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */
    fun removePhone(name: String, phone: String): Boolean {
        if (names.find { it.humanName == name } != null) {
            if (names.filter { it.humanName == name }.find { phone in it.numbers } != null) {
                names.filter { it.humanName == name }.map { it.numbers -= phone }
                return true
            }
            return false
        }
        return false
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> = names.find { it.humanName == name }?.numbers ?: setOf()

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? = names.find { phone in it.numbers }?.humanName

    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneBook) return false
        val otherBook = other.names
        for (human in this.names) {
            if (otherBook.find { it.humanName == human.humanName } != null) {
                val intersection = human.numbers.intersect(otherBook.find { it.humanName == human.humanName }!!.numbers)
                if ((intersection != human.numbers) ||
                    (intersection != otherBook.find { it.humanName == human.humanName }!!.numbers)
                ) return false
                otherBook -= otherBook.find { it.humanName == human.humanName }!!
            } else {
                return false
            }
        }
        if (otherBook.isNotEmpty()) return false
        return true
    }

    override fun hashCode(): Int {
        var result = 13
        for (human in names) {
            for (number in human.numbers) {
                result += number.hashCode()
            }
            result += human.humanName.hashCode()
        }
        return result
    }
}