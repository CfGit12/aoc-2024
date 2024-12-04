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
                listOf(point2D + Direction.NORTH_WEST, point2D, point2D + Direction.SOUTH_EAST), null
            ).toSet()
            val rightDiagonal = input.valuesForPoints(
                listOf(point2D + Direction.SOUTH_WEST, point2D, point2D + Direction.NORTH_EAST), null
            ).toSet()
            val mas = setOf('M', 'A', 'S')
            leftDiagonal == mas && rightDiagonal == mas
        }
    }

    fun Point2D.pointsInAllDirections(amount: Int): List<List<Point2D>> = listOf(
        listOf(this) + pointsInDirection(Direction.NORTH, amount),
        listOf(this) + pointsInDirection(Direction.NORTH_EAST, amount),
        listOf(this) + pointsInDirection(Direction.EAST, amount),
        listOf(this) + pointsInDirection(Direction.SOUTH_EAST, amount),
        listOf(this) + pointsInDirection(Direction.SOUTH, amount),
        listOf(this) + pointsInDirection(Direction.SOUTH_WEST, amount),
        listOf(this) + pointsInDirection(Direction.WEST, amount),
        listOf(this) + pointsInDirection(Direction.NORTH_WEST, amount)
    )

    fun Point2D.pointsInDirection(direction: Direction, amount: Int): List<Point2D> {
        return when (direction) {
            Direction.NORTH -> (1..amount).map { i -> copy(y = y - i) }
            Direction.EAST -> (1..amount).map { i -> copy(x = x + i) }
            Direction.SOUTH -> (1..amount).map { i -> copy(y = y + i) }
            Direction.WEST -> (1..amount).map { i -> copy(x = x - i) }
            Direction.NORTH_EAST -> (1..amount).map { i -> copy(x = x + i, y = y - i) }
            Direction.SOUTH_EAST -> (1..amount).map { i -> copy(x = x + i, y = y + i) }
            Direction.SOUTH_WEST -> (1..amount).map { i -> copy(x = x - i, y = y + i) }
            Direction.NORTH_WEST -> (1..amount).map { i -> copy(x = x - i, y = y - i) }
        }
    }

    override fun parse(inputStr: String): Grid<Char> =
        Grid.fromLines(inputStr.lines()) { it }
}