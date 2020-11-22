package sorting.handler

import sorting.InputOptions
import java.io.File
import java.util.*

class WordDataHandler(input: Scanner)
    : DataHandler(input) {

    override fun collect(): DataHandler {
        if (InputOptions.inputFile.isNotEmpty()) {
            File(InputOptions.inputFile).forEachLine { it ->
                it.split(" ").filter { it != "" }.forEach {
                    this.addValue(it)
                }
            }
        } else {
            while (this.input.hasNext()) {
                this.addValue(input.next())
            }
        }

        this.list.sortWith(this.getComparator())
        return this
    }
}