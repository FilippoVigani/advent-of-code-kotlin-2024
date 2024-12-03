import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val total = readInput("Day02")
        .count { input ->
            input.split(" ")
                .map { it.toInt() }
                .run {
                    val result = checkSafety()

                    println("\n$this $result")

                    when (result) {
                        ReactorResult.Safe -> true
                        is ReactorResult.Unsafe -> checkSafetyAroundIndex(result.faultyIndex)
                    }

                }
        }

    println(total)
}

private fun List<Int>.checkSafetyAroundIndex(faultyIndex: Int): Boolean{
    return (faultyIndex-1..faultyIndex+1).any {
        val list = this.withoutIndexOrNull(it)
        val adjustedResult = list?.checkSafety()
        println("Testing $list $adjustedResult")
        adjustedResult is ReactorResult.Safe
    }
}

private fun List<Int>.withoutIndexOrNull(index: Int) = if (index in indices){
    take(index) + drop(index + 1)
} else {
    null
}

sealed interface ReactorResult {
    data object Safe : ReactorResult
    data class Unsafe(val faultyIndex: Int) : ReactorResult
}

private fun Pair<Int, Int>.isStable() = when (abs(first - second)) {
    in 1..3 -> true
    else -> false
}

private fun List<Int>.checkSafety(): ReactorResult {
    if (size == 1) return ReactorResult.Safe
    var direction: Int? = null
    for (i in 0 until size - 1) {
        val level1 = get(i)
        val level2 = get(i+1)

        val currentDirection = (level1 - level2).sign
        if (currentDirection.sign == 0 || (direction != null && direction != currentDirection)) {
            return ReactorResult.Unsafe(i)
        }
        direction = currentDirection

        if (!(level1 to level2).isStable()) {
            return ReactorResult.Unsafe(i)
        }

    }
    return ReactorResult.Safe
}