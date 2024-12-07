object Day4 : AocDay<Grid<Char>>(4) {

    override fun part1(input: Grid<Char>) = input.keys.sumOf { point2D ->
        val directions = point2D.pointsInAllDirections(3)
        var count = 0
        for (direction in directions) {
            val values = input.valuesForPoints(direction, null)
            val str = values.joinToString("")
            if (str == "XMAS") count++
        }
        count
    }

    override fun part2(input: Grid<Char>) = input.entries.count { (point2D, char) ->
        if (char != 'A') {
            false
        } else {
            val leftDiagonal = input.valuesForPoints(
                listOf(point2D + Direction.NW, point2D, point2D + Direction.SE), null
            ).toSet()
            val rightDiagonal = input.valuesForPoints(
                listOf(point2D + Direction.SW, point2D, point2D + Direction.NE), null
            ).toSet()
            val mas = setOf('M', 'A', 'S')
            leftDiagonal == mas && rightDiagonal == mas
        }
    }

    fun Point2D.pointsInAllDirections(amount: Int): List<List<Point2D>> = listOf(
        listOf(this) + pointsInDirection(Direction.N, amount),
        listOf(this) + pointsInDirection(Direction.NE, amount),
        listOf(this) + pointsInDirection(Direction.E, amount),
        listOf(this) + pointsInDirection(Direction.SE, amount),
        listOf(this) + pointsInDirection(Direction.S, amount),
        listOf(this) + pointsInDirection(Direction.SW, amount),
        listOf(this) + pointsInDirection(Direction.W, amount),
        listOf(this) + pointsInDirection(Direction.NW, amount)
    )

    fun Point2D.pointsInDirection(direction: Direction, amount: Int): List<Point2D> {
        return when (direction) {
            Direction.N -> (1..amount).map { i -> copy(y = y - i) }
            Direction.E -> (1..amount).map { i -> copy(x = x + i) }
            Direction.S -> (1..amount).map { i -> copy(y = y + i) }
            Direction.W -> (1..amount).map { i -> copy(x = x - i) }
            Direction.NE -> (1..amount).map { i -> copy(x = x + i, y = y - i) }
            Direction.SE -> (1..amount).map { i -> copy(x = x + i, y = y + i) }
            Direction.SW -> (1..amount).map { i -> copy(x = x - i, y = y + i) }
            Direction.NW -> (1..amount).map { i -> copy(x = x - i, y = y - i) }
        }
    }

    override fun parse(inputStr: String): Grid<Char> =
        Grid.fromLines(inputStr.lines()) { it }
}