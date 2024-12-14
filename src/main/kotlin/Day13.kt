object Day13 : AocDay<List<Day13.ClawMachine>>(13) {

    override fun part1(input: List<ClawMachine>) =
        tokenSum(input)

    override fun part2(input: List<ClawMachine>): Any {
        return input.map {
            it.copy(
                prize = it.prize.copy(
                    x = it.prize.x + 10000000000000L,
                    y = it.prize.y + 10000000000000L
                )
            )
        }.let { tokenSum(it) }
    }

    fun tokenSum(machines: List<ClawMachine>) =
        machines.mapNotNull { solveSim(it.a.x, it.b.x, it.prize.x, it.a.y, it.b.y, it.prize.y) }
            .sumOf { (a, b) -> 3 * a + b }

    fun solveSim(a: Long, b: Long, e: Long, c: Long, d: Long, f: Long): Pair<Long, Long>? {
        val det = a * d - b * c

        val xBit = d * e - b * f
        val yBit = a * f - c * e

        if (xBit % det != 0L || yBit % det != 0L) return null

        return xBit / det to yBit / det
    }

    override fun parse(inputStr: String): List<ClawMachine> {
        var buttonRegex = """.*X\+(\d+).*\+(\d+)""".toRegex()
        val prizeRegex = """.*X=(\d+).*=(\d+)""".toRegex()
        val clawMachines = mutableListOf<ClawMachine>()
        for (block in inputStr.split("\n\n")) {
            val lines = block.lines()
            fun makePoint2DL(regex: Regex, str: String): Point2DL {
                val (x, y) = regex.find(str)!!.destructured
                return Point2DL(x.toLong(), y.toLong())
            }

            val buttonA = makePoint2DL(buttonRegex, lines[0])
            val buttonB = makePoint2DL(buttonRegex, lines[1])
            val prize = makePoint2DL(prizeRegex, lines[2])
            clawMachines.add(ClawMachine(buttonA, buttonB, prize))
        }
        return clawMachines
    }

    data class Point2DL(val x: Long, val y: Long)

    data class ClawMachine(val a: Point2DL, val b: Point2DL, val prize: Point2DL)

}