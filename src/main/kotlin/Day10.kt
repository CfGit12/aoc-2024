object Day10 : AocDay<Grid<Int>>(10) {


    override fun part1(grid: Grid<Int>): Any {
        val reachablePeaks = mutableMapOf<Point2D, Set<Point2D>>()

        fun calculateReachablePeaks(point2D: Point2D): Set<Point2D> {
            if (point2D in reachablePeaks) return reachablePeaks[point2D]!!

            val height = grid[point2D]!!

            if (height == 9) return setOf(point2D)

            val surroundingPoints = grid.getSurroundingPoints(point2D) { it == height + 1 }

            if (surroundingPoints.isEmpty()) return emptySet()

            val reachable = surroundingPoints.flatMap { calculateReachablePeaks(it) }.toSet()

            reachablePeaks[point2D] = reachable
            return reachable
        }

        val starts = grid.filter { (_, height) -> height == 0 }.keys
        val total = starts.sumOf { calculateReachablePeaks(it).size }
        return total
    }

    override fun part2(grid: Grid<Int>): Any {
        val scores = mutableMapOf<Point2D, Int>()

        fun calculateScore(point2D: Point2D): Int {
            if (point2D in scores) return scores[point2D]!!

            val height = grid[point2D]!!

            if (height == 9) return 1

            val surroundingPoints = grid.getSurroundingPoints(point2D) { it == height + 1 }

            if (surroundingPoints.isEmpty()) return 0

            val score = surroundingPoints.sumOf { calculateScore(it) }

            scores[point2D] = score
            return score
        }

        val starts = grid.filter { (_, height) -> height == 0 }.keys
        val total = starts.sumOf { calculateScore(it) }
        return total
    }

    override fun parse(inputStr: String): Grid<Int> =
        Grid.fromLines(inputStr.lines()) { if (it != '.') it.digitToInt() else -1 }

}