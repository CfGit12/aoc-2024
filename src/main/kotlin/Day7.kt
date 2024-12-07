typealias TargetAndNumbersList = List<Pair<Long, List<Long>>>
typealias TargetAndNumbers = Pair<Long, List<Long>>

object Day7 : AocDay<TargetAndNumbersList>(7) {

    override fun part1(targetAndNumbersList: TargetAndNumbersList) =
        targetAndNumbersList
            .filter { canMakeTarget(it) }
            .sumOf { it.first }

    override fun part2(targetAndNumbersList: TargetAndNumbersList) =
        targetAndNumbersList
            .filter { canMakeTarget2(it) }
            .sumOf { it.first }

    fun canMakeTarget(targetAndNumbers: TargetAndNumbers): Boolean {
        val (target, numbers) = targetAndNumbers

        fun calculation(currentTotal: Long, remainingNumbers: List<Long>): Boolean {
            if (remainingNumbers.isEmpty()) {
                return currentTotal == target
            }
            val next = remainingNumbers[0]
            if (calculation(currentTotal + next, remainingNumbers.drop(1))) return true
            if (calculation(currentTotal * next, remainingNumbers.drop(1))) return true
            return false
        }

        return calculation(numbers[0], numbers.drop(1))
    }

    fun canMakeTarget2(targetAndNumbers: TargetAndNumbers): Boolean {
        val (target, numbers) = targetAndNumbers

        fun calculation(currentTotal: Long, remainingNumbers: List<Long>): Boolean {
            if (currentTotal > target) return false
            if (remainingNumbers.isEmpty()) {
                return currentTotal == target
            }
            val next = remainingNumbers[0]
            if (calculation(currentTotal + next, remainingNumbers.drop(1))) return true
            if (calculation("$currentTotal$next".toLong(), remainingNumbers.drop(1))) return true
            if (calculation(currentTotal * next, remainingNumbers.drop(1))) return true
            return false
        }

        return calculation(numbers[0], numbers.drop(1))
    }

    override fun parse(inputStr: String): TargetAndNumbersList =
        inputStr.lines().map { line ->
            val (target, numbersStr) = line.split(": ")
            target.toLong() to numbersStr.split(" ").toLongs()
        }

}