fun main() {
    val rows = readInput("Day07")

    val expressions = rows.map {
        val result = it.split(": ").first().toLong()
        val values = it.split(": ").last().split(" ").map { it.toLong() }
        Expression(result, values)
    }

    var total = 0L
    expressions.forEach {
        if (eligible(it.result, it.values)){
            total += it.result
        }
    }

    println(total)

}

fun eligible(result: Long, values: List<Long>): Boolean{
    if (values.size == 1){
        return result == values.first()
    }
    val lastValue = values.last()
    val remaining = values.dropLast(1)

   return  if (result % lastValue == 0L){
        eligible(result - lastValue, remaining) || eligible(result / lastValue, remaining)
    } else {
        eligible(result - lastValue, remaining)
    }
}

data class Expression(
    val result: Long,
    val values: List<Long>,
)