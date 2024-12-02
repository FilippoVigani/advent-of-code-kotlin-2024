import java.util.stream.IntStream.range
import kotlin.math.abs

fun main() {
    val regex = Regex("(\\d+)\\s+(\\d+)")

    val sortedList1 = mutableListOf<Int>()
    val sortedList2 = mutableListOf<Int>()

    readInput("Day01")
        .forEach {
            val match = regex.matchEntire(it) ?: throw Exception()

            val number1 = match.groups[1]?.value?.toIntOrNull() ?: throw Exception()
            val number2 = match.groups[2]?.value?.toIntOrNull() ?: throw Exception()

            sortedList1.addSorted(number1)
            sortedList2.addSorted(number2)
        }

    var total = 0
    for (i in range(0, sortedList1.size)){
        total += abs(sortedList1[i] - sortedList2[i])
    }

    println(total)
}

private fun MutableList<Int>.addSorted(element: Int) = this.add(this.binarySearch(element).toInsertionPoint(), element)

private fun Int.toInsertionPoint() = abs(this + 1)
