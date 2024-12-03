fun main() {
    val regex = Regex("mul\\((\\d+),(\\d+)\\)")

    val memory = readInputBlock("Day03")

    val matches = regex.findAll(memory)

    val total = matches.sumOf { matchResult ->
        val a = matchResult.groups[1]!!.value.toInt()
        val b = matchResult.groups[2]!!.value.toInt()
        a * b
    }

    println(total)
}
