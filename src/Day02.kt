import kotlin.math.abs

fun main() {
    val total = readInput("Day02")
        .count { input ->
            input.split(" ")
                .map { it.toInt() }
                .asSequence()
                .windowed(2)
                .map { it.first() to it.last() }
                .run {
                    allStable() && (allDecreasing() || allIncreasing())
                }
        }

    println(total)
}

private fun Sequence<Pair<Int, Int>>.allStable() = all {
    when (abs(it.first - it.second)) {
        in 1..3 -> true
        else -> false
    }
}

private fun Sequence<Pair<Int, Int>>.allIncreasing() = all { it.first < it.second }
private fun Sequence<Pair<Int, Int>>.allDecreasing() = all { it.first > it.second }
