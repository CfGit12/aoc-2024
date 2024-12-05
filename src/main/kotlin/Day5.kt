typealias Rules = Map<Int, List<Int>>
typealias RulesAndInstructions = Pair<Rules, List<List<Int>>>

object Day5 : AocDay<RulesAndInstructions>(5) {
    override fun part1(input: RulesAndInstructions) = input.second.sumOf { instructions ->
        isValid(instructions, input.first)
    }

    fun isValid(instructions: List<Int>, rules: Rules): Int {
        var isValid = true
        var i = 0
        while (isValid && i < instructions.size) {
            var x = instructions[i]
            var remainingInstructions = instructions.drop(i + 1)
            if (remainingInstructions.any { rule -> x in (rules[rule] ?: emptyList()) }) {
                isValid = false
            }
            i++
        }
        return if (isValid) instructions[instructions.size / 2] else 0
    }

    override fun part2(input: RulesAndInstructions): Any {
        val rules = input.first

        val remaining = input.second.filter { isValid(it, input.first) == 0 }

        val sorted = remaining.map { line ->
            line.sortedWith { x, y ->
                when {
                    x in rules[y].orEmpty() -> -1
                    y in rules[x].orEmpty() -> 1
                    else -> 0
                }
            }
        }

        return sorted.sumOf { it[it.size / 2] }
    }

    override fun parse(inputStr: String): RulesAndInstructions {
        val (rulesStr, instructionsStr) = inputStr.split("\n\n")
        val rules = mutableMapOf<Int, List<Int>>()
        println(rulesStr.lines().size)
        println(rulesStr.lines().toSet().size)
        val x = rulesStr.lines().map { it.split("|") }
        val reversed = x.map { it.reversed() }
        val duplicates = x.filter { it in reversed }
        println("Duplicates: $duplicates")
        for (ruleStr in rulesStr.lines()) {
            val (l, r) = ruleStr.split("|")
            rules.merge(l.toInt(), listOf(r.toInt())) { list, new -> list + new }
        }
        val instructions = instructionsStr.lines().map { it.split(",").toInts() }
        return rules to instructions
    }
}