object Day9 : AocDay<List<Int>>(9) {
    override fun part1(input: List<Int>): Any {
        val memory = input.toMutableList()

        var leftIndex = 0
        var rightIndex = memory.size - 1
        while (true) {
            while (memory[leftIndex] != -1) leftIndex++
            while (memory[rightIndex] == -1) rightIndex--
            if (leftIndex > rightIndex) break
            memory[leftIndex] = memory[rightIndex]
            memory[rightIndex] = -1
        }
        return memory.takeWhile { it != -1 }.mapIndexed { i, n -> i * n.toLong() }.sum()
    }

    override fun part2(input: List<Int>): Any {
        val memory = input.toMutableList()

        var maxFileSize = Int.MAX_VALUE
        var rightIndex = memory.size - 1
        while (rightIndex > 0) {
            if (memory[rightIndex] == -1 || memory[rightIndex] > maxFileSize) {
                rightIndex--
                continue
            }
            val file = memory[rightIndex]
            maxFileSize = file
            var size = 1
            while (rightIndex - 1 >= 0 && memory[rightIndex - 1] == file) {
                size++
                rightIndex--
            }

            fun indexOfEnoughSpace(amount: Int, limit: Int): Int {
                var total = 0
                for (i in 0..<limit) {
                    if (memory[i] == -1) {
                        total++
                        if (total == amount) return i - total + 1
                    } else total = 0
                }
                return -1
            }

            val emptySpace = indexOfEnoughSpace(size, rightIndex)

            if (emptySpace != -1) {
                for (i in emptySpace..emptySpace + size - 1) {
                    memory[i] = file
                }
                for (i in rightIndex..rightIndex + size - 1) {
                    memory[i] = -1
                }
            }

            rightIndex--
        }

        return memory.mapIndexed { index, n ->
            if (n != -1) n.toLong() * index else 0
        }.sum()
    }

    override fun parse(inputStr: String): List<Int> {
        val memory = mutableListOf<Int>()
        for ((i, n) in inputStr.withIndex()) {
            val charToAdd = if (i % 2 == 0) i / 2 else -1
            repeat(n.digitToInt()) { memory.add(charToAdd) }
        }
        return memory
    }
}