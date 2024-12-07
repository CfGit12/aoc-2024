object Day6 : AocDay<Grid<Char>>(6) {

    override fun part1(grid: Grid<Char>) =
        walk(grid).first.size

    override fun part2(grid: Grid<Char>) =
        walk(grid).first
            .filterNot { it == grid.posOf('^') }
            .count { walk(grid.modifiedWithPoint(it, '#')).second }

    fun walk(grid: Grid<Char>): Pair<Set<Point2D>, Boolean> {
        var position = grid.posOf('^')
        var direction = Direction.N
        var visited = mutableSetOf<Pair<Point2D, Direction>>()
        while (grid[position] != null && (position to direction !in visited)) {
            visited += position to direction
            val ahead = position + direction

            if (grid[ahead] == '#') direction = direction.rotate90()
            else position = ahead
        }
        return visited.map { it.first }.toSet() to (grid[position] != null)
    }

    fun Direction.rotate90() = when (this) {
        Direction.N -> Direction.E
        Direction.E -> Direction.S
        Direction.S -> Direction.W
        Direction.W -> Direction.N
        else -> throw NotImplementedError("Doesn't go this way")
    }

    override fun parse(inputStr: String): Grid<Char> =
        Grid.fromLines(inputStr.lines()) { it }

}