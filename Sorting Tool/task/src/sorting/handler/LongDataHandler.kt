package sorting.handler

import sorting.InputOptions
import java.io.File
import java.util.*

class LongDataHandler(input: Scanner)
    : DataHandler(input) {

    override fun collect(): DataHandler {
        if (InputOptions.inputFile.isNotEmpty()) {
            File(InputOptions.inputFile).forEachLine { it ->
                it.split(" ").filter { it != "" }.forEach {
                    if (it.toIntOrNull() == null) {
                        println("\"$it\" is not a long. It will be skipped.")
                    } else {
                        this.addValue(it)
                    }
                }
            }
        } else {
            while (this.input.hasNext()) {
                val value = input.next()

                if (value.toIntOrNull() == null) {
                    println("\"$value\" is not a long. It will be skipped.")
                    continue
                }

                this.addValue(value)
            }
        }

        this.list.sortWith(this.getComparator())
        return this
    }
}