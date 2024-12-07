fun main() {
    val rows = readInput("Day06")

    var direction: Pair<Int, Int> = 0 to 0
    var position: Pair<Int, Int> = -1 to -1

    val north = -1 to 0
    val east = 0 to 1
    val south = 1 to 0
    val west = 0 to -1
    for (columnIndex in 0 until rows[0].length){
        for (rowIndex in rows.indices){
            val marker = rows[rowIndex][columnIndex]

            direction = when(marker){
                '^' -> north
                '>' -> east
                'v' -> south
                '<' -> west
                else -> continue
            }

            position = rowIndex to columnIndex
        }
    }

    val visitedPositions = mutableSetOf<Pair<Int, Int>>()

    while (position.first in rows.indices && position.second in 0 until rows[0].length){
        visitedPositions.add(position)

        val nextPosition = position.first + direction.first to position.second + direction.second

        if (rows.getOrNull(nextPosition.first)?.getOrNull(nextPosition.second) == '#'){
            direction = when(direction){
                north -> east
                east -> south
                south -> west
                west -> north
                else -> throw IllegalStateException()
            }
        } else {
            position = nextPosition
        }

    }

    println(visitedPositions.size)

}