fun main() {
    val regex = Regex("mul\\((\\d+),(\\d+)\\)|(don't\\(\\))|(do\\(\\))")

    val memory = readInputBlock("Day03")

    val matches = regex.findAll(memory)

    var enabled = true
    var total = 0
    matches.forEach { matchResult ->
        when(matchResult.groups[0]!!.value){
            "don't()" -> enabled = false
            "do()" -> enabled = true
            else -> {
                if (enabled){
                    val a = matchResult.groups[1]!!.value.toInt()
                    val b = matchResult.groups[2]!!.value.toInt()
                    total += a * b
                }
            }
        }
    }

    println(total)
}
