fun main() {
    val rows = readInput("Day05").asSequence()

    val pagePairs = rows.takeWhile { it.contains("|") }.map { it.split("|") }.map { it[0] to it[1] }
    val updates = rows.dropWhile { !it.contains(",") }.map { it.split(",") }

    val validUpdates = updates.filter { update ->
        pagePairs.all { pagePair ->
            val pageIndex1 = update.indexOf(pagePair.first)
            val pageIndex2 = update.indexOf(pagePair.second)

            pageIndex1 < pageIndex2 || pageIndex1 == -1 || pageIndex2 == -1

        }
    }

    val total = validUpdates.sumOf { update ->
        update[update.size/2].toInt()
    }

    println(total)
}
