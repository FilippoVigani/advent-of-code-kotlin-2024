fun main() {
    val rows = readInput("Day04")

    var total = 0
    for (columnIndex in 0 until rows[0].length){
        for (rowIndex in rows.indices){
            if (rows[rowIndex][columnIndex] == 'A'){
                total += countSurroundingXMas(rows, rowIndex, columnIndex)
            }
        }
    }

    println(total)
}

private fun countSurroundingXMas(rows: List<String>, rowIndex: Int, columnIndex: Int): Int {
    val wordSearch = "MS"

    val diagonal1 = (rows.getOrNull(rowIndex-1)?.getOrNull(columnIndex-1).toString()) +
            (rows.getOrNull(rowIndex+1)?.getOrNull(columnIndex+1).toString())
    val diagonal2 = (rows.getOrNull(rowIndex-1)?.getOrNull(columnIndex+1).toString()) +
            (rows.getOrNull(rowIndex+1)?.getOrNull(columnIndex-1).toString())

    val isXMas = (diagonal1 == wordSearch || diagonal1 == wordSearch.reversed()) &&  (diagonal2 == wordSearch || diagonal2 == wordSearch.reversed())

    return if (isXMas)
        1
    else 0
}
