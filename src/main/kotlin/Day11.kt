object Day11 : AocDay<List<Long>>(11) {

    override fun part1(input: List<Long>): Any {
        var list = input
        repeat(25) {
            val newList = mutableListOf<Long>()
            for (stone in list) {
                newList.addAll(blink(stone))
            }
            list = newList
        }
        return list.size
    }

    fun blink(stone: Long): List<Long> = when {
        stone == 0L -> listOf(1)
        stone.isEventDigits() -> stone.split()
        else -> listOf(stone * 2024)
    }

    fun Long.isEventDigits() = this.toString().length % 2 == 0
    fun Long.split(): List<Long> {
        val s = this.toString()
        val length = s.length
        val left = s.take(length / 2).toLong()
        val right = s.takeLast(length / 2).toLong()
        return listOf(left, right)
    }

    override fun part2(input: List<Long>): Any {
        val cache = mutableMapOf<Pair<Long, Int>, Long>()

        fun calculateTotal(stone: Long, level: Int): Long {
            return cache.getOrPut(stone to level) {
                if (level == 0) 1L
                else {
                    blink(stone).sumOf { calculateTotal(it, level - 1) }
                }
            }
        }

        return input.sumOf { calculateTotal(it, 75) }
    }

    override fun parse(inputStr: String): List<Long> =
        inputStr.split(" ").toLongs()

}