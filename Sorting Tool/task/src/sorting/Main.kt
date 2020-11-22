package sorting

import sorting.handler.DataHandler

fun main(args: Array<String>) {

    try {
        InputOptions.parse(args)
        DataHandler.Factory.get().collect().report()
    } catch (e: SortingToolException) {
        println(e.message)
    }
}
