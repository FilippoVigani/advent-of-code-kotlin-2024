import java.util.TreeMap

fun main() {
    val regex = Regex("(\\d+)\\s+(\\d+)")

    val sortedMap1 = TreeMap<Int, Int>()
    val sortedMap2 = TreeMap<Int, Int>()

    readInput("Day01")
        .forEach {
            val match = regex.matchEntire(it) ?: throw Exception()

            val number1 = match.groups[1]?.value?.toIntOrNull() ?: throw Exception()
            val number2 = match.groups[2]?.value?.toIntOrNull() ?: throw Exception()

            sortedMap1.merge(number1, 1) { a, b -> a + b }
            sortedMap2.merge(number2, 1) { a, b -> a + b }
        }


    val total = sortedMap1.keys.sumOf {
        it * sortedMap2.getOrDefault(it, 0)
    }

    println(total)
}
