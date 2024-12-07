fun main() {
    val rows = readInput("Day06")

    var initialDirection: Pair<Int, Int> = 0 to 0
    var initialPosition: Pair<Int, Int> = -1 to -1

    val north = -1 to 0
    val east = 0 to 1
    val south = 1 to 0
    val west = 0 to -1
    for (columnIndex in 0 until rows[0].length){
        for (rowIndex in rows.indices){
            val marker = rows[rowIndex][columnIndex]

            initialDirection = when(marker){
                '^' -> north
                '>' -> east
                'v' -> south
                '<' -> west
                else -> continue
            }

            initialPosition = rowIndex to columnIndex
        }
    }

    var loops = 0
    for (columnIndex in 0 until rows[0].length){
        for (rowIndex in rows.indices){
            val obstaclePosition = rowIndex to columnIndex
            var direction = initialDirection
            var position = initialPosition
            val visitedStates = mutableSetOf<VisitedState>()

            while (position.first in rows.indices && position.second in 0 until rows[0].length){
                val currentState = VisitedState(
                    direction = direction,
                    position = position,
                )
                if (visitedStates.contains(currentState)){
                    loops += 1
                    break
                }
                visitedStates.add(currentState)

                val nextPosition = position.first + direction.first to position.second + direction.second

                val nextCell = rows.getOrNull(nextPosition.first)?.getOrNull(nextPosition.second)
                if (nextCell == '#' || nextPosition == obstaclePosition){
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
        }
    }

    println(loops)

}

data class VisitedState(
    val direction: Pair<Int, Int>,
    val position: Pair<Int, Int>,
)