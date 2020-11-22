package sorting.handler

import sorting.DataType
import sorting.InputOptions
import sorting.SortingType
import java.io.File
import java.util.*

data class Entity(var count: Int, val value: String)

open abstract class DataHandler(val input: Scanner) {

    val list = mutableListOf<Entity>()

    object Factory {
        fun get(): DataHandler {
            val scanner = Scanner(System.`in`)
            return when (InputOptions.dataType) {
                DataType.LONG -> LongDataHandler(scanner)
                DataType.LINE -> LineDataHandler(scanner)
                DataType.WORD -> WordDataHandler(scanner)
            }
        }
    }

    protected open fun countComparator(): Comparator<Entity> {
        return Comparator<Entity> { entry1, entry2 ->
            when {
                entry1.count < entry2.count -> -1
                entry1.count > entry2.count -> 1
                else -> {
                    if (InputOptions.dataType == DataType.LONG) {
                        when {
                            entry1.value.toInt() < entry2.value.toInt() -> -1
                            entry1.value.toInt() > entry2.value.toInt() -> 1
                            else -> 0
                        }
                    } else {
                        when {
                            entry1.value < entry2.value -> -1
                            entry1.value > entry2.value -> 1
                            else -> 0
                        }
                    }
                }
            }
        }
    }

    protected open fun naturalComparator(): Comparator<Entity> {
        return Comparator<Entity> { entry1, entry2 ->
            if (InputOptions.dataType == DataType.LONG) {
                when {
                    entry1.value.toInt() < entry2.value.toInt() -> -1
                    entry1.value.toInt() > entry2.value.toInt() -> 1
                    else -> 0
                }
            } else {
                when {
                    entry1.value < entry2.value -> -1
                    entry1.value > entry2.value -> 1
                    else -> 0
                }
            }
        }
    }

    protected fun stringComparator(): Comparator<String> {
        return Comparator<String> { str1, str2 ->
            val lhs = str1.length
            val rhs = str2.length
            when {
                lhs < rhs -> -1
                lhs > rhs -> 1
                else -> {
                    when {
                        str1 < str2 -> -1
                        str1 > str2 -> 1
                        else -> 0
                    }
                }
            }
        }
    }

    protected fun addValue(value: String) {
        val index = this.list.indexOfFirst { it.value == value }
        if (index != -1) {
            this.list[index].count = this.list[index].count + 1
        } else {
            val entity = Entity(count = 1, value)
            this.list.add(entity)
        }
    }

    protected fun getTotal(): Int {
        return this.list.fold(0) { total, entity ->
            total + entity.count
        }
    }

    protected fun getComparator(): Comparator<Entity> {
        return when (InputOptions.sortingType) {
            SortingType.NATURAL -> {
                this.naturalComparator()
            }
            SortingType.COUNT -> {
                this.countComparator()
            }
        }
    }

    open fun report() {

        var report = "Total numbers: ${this.getTotal()}.\n"
        when (InputOptions.sortingType) {
            SortingType.NATURAL -> {
                report += "Sorted data: ${this.list.joinToString("") { "${it.value} ".repeat(it.count) }}\n"
            }
            SortingType.COUNT -> {
                this.list.forEach {
                    report += "${it.value}: ${it.count} time(s), ${it.count * 100 / this.getTotal()}%\n"
                }
            }
        }
        if (InputOptions.outputFile.isNotEmpty()) {
            File(InputOptions.outputFile).writeText(report)
        } else println(report)
    }

    open abstract fun collect(): DataHandler
}