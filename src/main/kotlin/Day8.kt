object Day8 : AocDay<Grid<Char>>(8) {

    override fun part1(grid: Grid<Char>): Any {
        val gridPositions = grid.keys
        val frequencies = grid.values.filterNot { it == '.' }.toSet()
        val antinodes = mutableSetOf<Point2D>()
        for (frequency in frequencies) {
            val positions = grid.filterValues { it == frequency }.keys.toList()
            val pairs = generatePairs(positions)
            for ((first, second) in pairs) {
                fun addIfValid(antinode: Point2D) {
                    if (antinode in gridPositions) antinodes.add(antinode)
                }

                val dx = second.x - first.x
                val dy = second.y - first.y
                addIfValid(first.copy(x = first.x - dx, y = first.y - dy))
                addIfValid(second.copy(x = second.x + dx, y = second.y + dy))
            }
        }

        return antinodes.size
    }

    fun generatePairs(list: List<Point2D>): List<Pair<Point2D, Point2D>> = buildList {
        for (i in 0..(list.size - 2)) {
            for (j in (i + 1)..(list.size - 1)) {
                add(list[i] to list[j])
            }
        }
    }

    override fun part2(grid: Grid<Char>): Any {
        val gridPositions = grid.keys
        val frequencies = grid.values.filterNot { it == '.' }.toSet()
        val antinodes = mutableSetOf<Point2D>()
        for (frequency in frequencies) {
            val positions = grid.filterValues { it == frequency }.keys.toList()
            val pairs = generatePairs(positions)
            for ((first, second) in pairs) {
                fun addIfValid(antinode: Point2D) {
                    if (antinode in gridPositions) antinodes.add(antinode)
                }

                val dx = second.x - first.x
                val dy = second.y - first.y

                fun addScaledAntinodes(scale: Int) {
                    addIfValid(first.copy(x = first.x - (dx * scale), y = first.y - (dy * scale)))
                    addIfValid(second.copy(x = second.x + (dx * scale), y = second.y + (dy * scale)))
                }

                for (i in 1..20) {
                    addScaledAntinodes(i)
                }
                antinodes.add(first)
                antinodes.add(second)
            }
        }

        return antinodes.size
    }

    override fun parse(inputStr: String) =
        Grid.fromLines(inputStr.lines()) { it }

}