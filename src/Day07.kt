fun main() {
    val rows = readInput("Day07")

    val expressions = rows.map {
        val result = it.split(": ").first().toLong()
        val values = it.split(": ").last().split(" ").map { it.toLong() }
        Expression(result, values)
    }

    var total = 0L
    expressions.forEach {
        if (eligible(it.result, it.values)) {
            total += it.result
        }
    }

    println(total)

}

fun eligible(result: Long, values: List<Long>): Boolean {
    if (result < 0L) return false
    if (values.size == 1) {
        return result == values.first()
    }
    val lastValue = values.last()
    val remaining = values.dropLast(1)

    val concatenable = result.toString().endsWith(lastValue.toString()) && result != lastValue
    val divisible = result % lastValue == 0L

    return eligible(result - lastValue, remaining) ||
            concatenable && eligible(
        result = (result.toString().dropLast(lastValue.toString().length)).toLong(),
        values = remaining,
    ) ||
            divisible && eligible(result / lastValue, remaining)

}

data class Expression(
    val result: Long,
    val values: List<Long>,
)