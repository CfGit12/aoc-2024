import kotlin.math.abs

private typealias ListPair = Pair<List<Int>, List<Int>>

object Day1 : AocDay<ListPair>(1) {

    override fun part1(input: ListPair): Any {
        val lhs = input.first.sorted()
        val rhs = input.second.sorted()
        return lhs.zip(rhs).sumOf { abs(it.second - it.first) }
    }

    override fun part2(input: ListPair): Any {
        val lhs = input.first
        val rhs = input.second

        return lhs.fold(0) { acc, i -> acc + (i * rhs.count { it == i }) }
    }

    override fun parse(inputStr: String): ListPair {
        val lines = inputStr.lines().map { it.split("   ") }
        val lhs = lines.map { it[0].toInt() }
        val rhs = lines.map { it[1].toInt() }
        return lhs to rhs
    }

}