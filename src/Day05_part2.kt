fun main() {
    val rows = readInput("Day05").asSequence()

    val pagePairs = rows.takeWhile { it.contains("|") }.map { it.split("|") }.map { it[0] to it[1] }
    val updates = rows.dropWhile { !it.contains(",") }.map { it.split(",") }

    val invalidUpdates = updates.filter { update ->
        !pagePairs.all { pagePair ->
            val pageIndex1 = update.indexOf(pagePair.first)
            val pageIndex2 = update.indexOf(pagePair.second)

            pageIndex1 < pageIndex2 || pageIndex1 == -1 || pageIndex2 == -1

        }
    }

    val sortedInvalidUpdates = invalidUpdates.map { update ->
        update.sortedWith(object : Comparator<String>{
            override fun compare(page1: String, page2: String): Int {
                val rule = pagePairs.find { setOf(it.first, it.second) == setOf(page1, page2) }

                if (rule == null) return 0

               return if (rule.first == page1) -1 else 1
            }

        })
    }

    val total = sortedInvalidUpdates.sumOf { update ->
        update[update.size/2].toInt()
    }

    println(total)
}
