package sorting

enum class DataType(val title: String) {
    LONG("long"),
    LINE("line"),
    WORD("word");
    companion object {
        fun findByName(name: String): DataType {
            for (enum in DataType.values()) {
                if (name == enum.title) return enum
            }

            throw Exception("Wrong type")
        }
    }
}

enum class SortingType(val title: String) {
    NATURAL("natural"),
    COUNT("byCount");
    companion object {
        fun findByName(name: String): SortingType {
            for (enum in SortingType.values()) {
                if (name == enum.title) return enum
            }

            throw Exception("Wrong type")
        }
    }
}

object InputOptions {
    var dataType: DataType = DataType.LONG
    var sortingType: SortingType = SortingType.NATURAL
    var inputFile: String = ""
    var outputFile: String = ""

    fun parse(args: Array<String>) {

        val dataTypeIndex = args.indexOf("-dataType")
        val sortingTypeIndex = args.indexOf("-sortingType")
        val inputFileIndex = args.indexOf("-inputFile")
        val outputFileIndex = args.indexOf("-outputFile")

        if (dataTypeIndex != -1) {
            val dataType = args.getOrNull(dataTypeIndex + 1)
            dataType ?: throw SortingToolException("No data type defined!")
            InputOptions.dataType = DataType.findByName(args[dataTypeIndex + 1])
        }
        if (sortingTypeIndex != -1) {
            val sortingType = args.getOrNull(sortingTypeIndex + 1)
            sortingType ?: throw SortingToolException("No sorting type defined!")
            InputOptions.sortingType = SortingType.findByName(args[sortingTypeIndex + 1])
        }
        if (inputFileIndex != -1) {
            val inputFile = args.getOrNull(inputFileIndex + 1)
            inputFile ?: throw SortingToolException("No input file defined!")
            InputOptions.inputFile = inputFile
        }
        if (outputFileIndex != -1) {
            val outputFile = args.getOrNull(outputFileIndex + 1)
            outputFile ?: throw SortingToolException("No output file defined!")
            InputOptions.outputFile = outputFile
        }

        args.forEach {
            if (it.matches(Regex("-[a-z]+")) && it != "-dataType" && it != "-sortingType")
                println("\"$it\" is not a valid parameter. It will be skipped.")
        }
    }
}