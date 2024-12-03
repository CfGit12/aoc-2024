object Day3 : AocDay<String>(3) {

    override fun part1(input: String): Any {
        val regex = """mul\(\d+,\d+\)""".toRegex()
        val mulPairs =
            regex.findAll(input).map { it.value.toMulPair() }
        return mulPairs.sumOf { it.first * it.second }
    }

    override fun part2(input: String): Any {
        val regex = """mul\(\d+,\d+\)|do\(\)|don't\(\)""".toRegex()
        var sum = 0
        var enabled = true
        for (inst in regex.findAll(input).map { it.value }) {
            when (inst) {
                "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> {
                    if (enabled) {
                        sum += inst.toMulPair().let { (a, b) -> a * b }
                    }
                }
            }
        }
        return sum
    }

    override fun parse(inputStr: String) = inputStr

    private fun String.toMulPair(): Pair<Int, Int> {
        val (a, b) = this.substringBetween("(", ")").split(",").toInts()
        return a to b
    }

}