fun main() {
    val rows = readInput("Day04")

    var total = 0
    for (columnIndex in 0 until rows[0].length){
        for (rowIndex in rows.indices){
            if (rows[rowIndex][columnIndex] == 'X'){
                total += countSurroundingXmas(rows, rowIndex, columnIndex)
            }
        }
    }

    println(total)
}

private fun countSurroundingXmas(rows: List<String>, rowIndex: Int, columnIndex: Int): Int {
    val directions = arrayOf(-1, 0, 1)
    val wordSearch = "MAS"

    var total = 0
    directions.forEach { horizontal ->
        directions.forEach { vertical ->
            var currentRowIndex = rowIndex
            var currentColumnIndex = columnIndex
            var index = 0
            var found = true
            do {
                currentRowIndex += vertical
                currentColumnIndex += horizontal

                val character = rows.getOrNull(currentRowIndex)?.getOrNull(currentColumnIndex)

                if (character != wordSearch[index]){
                    found = false
                }
                index++
            } while (found && index < (wordSearch.length))
            if (found){
                total++
            }
        }
    }

    return total
}
