import kotlin.math.abs

private val input = readFileAsLines("1.txt")
    .map { it.split("   ")  }

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int {
    val lhs = input.map { it[0].toInt() }.sorted()
    val rhs = input.map { it[1].toInt() }.sorted()
    return lhs.zip(rhs).sumOf { abs( it.second - it.first) }
}

private fun part2(): Int {
    val lhs = input.map { it[0].toInt() }
    val rhs = input.map { it[1].toInt() }

    return lhs.fold(0) { acc, i -> acc + (i * rhs.count { it == i }) }
}