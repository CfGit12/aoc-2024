import kotlin.math.abs

typealias ListListInts = List<List<Int>>

object Day2 : AocDay<ListListInts>(2) {

    override fun part1(input: ListListInts) =
        input.count { it.isSafe1() }

    override fun part2(input: ListListInts) =
        input.count { it.isSafe2() }

    private fun List<Int>.isSafe1(): Boolean {
        val incDecPredicate: (Pair<Int, Int>) -> Boolean =
            if (this[0] > this[1])
                { (x, y) -> x > y }
            else
                { (x, y) -> x < y }

        val isWithinRangePredicate =
            { (x, y): Pair<Int, Int> -> abs(x - y) in 1..3 }

        return this.zipWithNext().all { incDecPredicate(it) && isWithinRangePredicate(it) }
    }

    private fun List<Int>.isSafe2(): Boolean {
        val subLists = mutableListOf<List<Int>>()
        for (i in this.indices) {
            subLists.add(this.filterIndexed { j, _ -> j != i })
        }
        return subLists.any { it.isSafe1() }
    }

    override fun parse(inputStr: String): ListListInts =
        inputStr.lines().map { string -> string.split(" ").map { it.toInt() } }

}