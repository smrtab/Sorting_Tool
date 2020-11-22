package sorting.handler

import sorting.InputOptions
import sorting.SortingType
import java.io.File
import java.util.*

class LineDataHandler(input: Scanner)
    : DataHandler(input) {

    override fun collect(): DataHandler {

        if (InputOptions.inputFile.isNotEmpty()) {
            File(InputOptions.inputFile).forEachLine { it ->
                this.addValue(it)
            }
        } else {
            while (this.input.hasNextLine()) {
                this.addValue(input.nextLine())
            }
        }

        this.list.sortWith(this.getComparator())
        return this
    }

    override fun report() {

        var report = "Total lines: ${this.getTotal()}.\n"
        when (InputOptions.sortingType) {
            SortingType.NATURAL -> {
                report += "Sorted data:\n"
                this.list.forEach {
                    for (i in 1..it.count) {
                        report += "${it.value}\n"
                    }
                }
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
}