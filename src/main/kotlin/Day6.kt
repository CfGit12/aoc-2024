object Day6 : AocDay<Grid<Char>>(6) {

    override fun part1(grid: Grid<Char>): Any {
        var position = grid.posOf('^')
        var direction = Direction.N
        var ahead = grid[position + direction]
        var visited = mutableSetOf(position)
        while (ahead != null) {
            if (ahead == '#') {
                direction = direction.rotate90()
                ahead = grid[position + direction]
                continue
            }
            position = position + direction
            visited.add(position)
            ahead = grid[position + direction]
        }
        return visited.size
    }

    override fun part2(grid: Grid<Char>): Any {
        val possibleObstacles = buildSet {
            for (x in 0..grid.highestX) {
                for (y in 0..grid.highestY) {
                    add(Point2D(x, y))
                }
            }
            remove(grid.posOf('^'))
        }
        return possibleObstacles.count { containsALoop(grid.modifiedWithPoint(it, '#')) }
    }

    fun containsALoop(grid: Grid<Char>): Boolean {
        var position = grid.posOf('^')
        var direction = Direction.N
        var ahead = grid[position + direction]
        var visited = mutableSetOf<Pair<Point2D, Direction>>()
        var looped = false
        while (ahead != null) {
            //println(position to direction)
            if ((position to direction) in visited) {
                looped = true
                break
            }
            if (ahead == '#') {
                direction = direction.rotate90()
                ahead = grid[position + direction]
                continue
            }
            visited.add(position to direction)
            position = position + direction
            ahead = grid[position + direction]
        }
        //println("returning $looped")
        return looped
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