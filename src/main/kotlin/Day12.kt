object Day12 : AocDay<Grid<Char>>(12) {

    data class Group(val points: Set<Point2D>) {
        val perimeter = points.sumOf { 4 - (it.surroundingPoints() intersect points).size }
        val area = points.size
        val price = perimeter * area
        val discountPrice = numberOfFences * area

        val numberOfFences: Int
            get() {
                val xRange = points.minOf { it.x }..points.maxOf { it.x }
                val yRange = points.minOf { it.y }..points.maxOf { it.y }

                var numberOfFences = 0
                // Scan across looking up
                var makingFence = false
                for (y in yRange) {
                    for (x in xRange) {
                        val containsPoint = points.contains(Point2D(x, y))
                        val hasPointAbove = points.contains(Point2D(x, y) + Direction.N)
                        if (containsPoint && !hasPointAbove) {
                            makingFence = true
                        } else {
                            if (makingFence) {
                                numberOfFences++
                                makingFence = false
                            }
                        }
                    }
                    if (makingFence) {
                        numberOfFences++
                        makingFence = false
                    }
                }

                // Scan across looking down
                makingFence = false
                for (y in yRange) {
                    for (x in xRange) {
                        val containsPoint = points.contains(Point2D(x, y))
                        val hasPointAbove = points.contains(Point2D(x, y) + Direction.S)
                        if (containsPoint && !hasPointAbove) {
                            makingFence = true
                        } else {
                            if (makingFence) {
                                numberOfFences++
                                makingFence = false
                            }
                        }
                    }
                    if (makingFence) {
                        numberOfFences++
                        makingFence = false
                    }
                }

                // Scan down looking right
                makingFence = false
                for (x in xRange) {
                    for (y in yRange) {
                        val containsPoint = points.contains(Point2D(x, y))
                        val hasPointAbove = points.contains(Point2D(x, y) + Direction.E)
                        if (containsPoint && !hasPointAbove) {
                            makingFence = true
                        } else {
                            if (makingFence) {
                                numberOfFences++
                                makingFence = false
                            }
                        }
                    }
                    if (makingFence) {
                        numberOfFences++
                        makingFence = false
                    }
                }

                // Scan down looking left
                makingFence = false
                for (x in xRange) {
                    for (y in yRange) {
                        val containsPoint = points.contains(Point2D(x, y))
                        val hasPointAbove = points.contains(Point2D(x, y) + Direction.W)
                        if (containsPoint && !hasPointAbove) {
                            makingFence = true
                        } else {
                            if (makingFence) {
                                numberOfFences++
                                makingFence = false
                            }
                        }
                    }
                    if (makingFence) {
                        numberOfFences++
                        makingFence = false
                    }
                }

                return numberOfFences
            }
    }

    fun generateGroups(grid: Grid<Char>): List<Group> {
        val seen = mutableSetOf<Point2D>()
        val groups = mutableListOf<Group>()

        for (point in grid.keys) {
            if (point in seen) continue

            val groupPoints = mutableSetOf<Point2D>()
            val toVisit = mutableListOf<Point2D>(point)
            while (toVisit.isNotEmpty()) {
                val p = toVisit.removeFirst()
                if (p in seen) continue
                seen.add(p)
                groupPoints.add(p)
                val c = grid[p]!!
                var surrounding = grid.getSurroundingPoints(p) { c == it }
                toVisit.addAll(surrounding)
            }
            groups.add(Group(groupPoints))
        }
        return groups
    }

    override fun part1(input: Grid<Char>) =
        generateGroups(input).sumOf { it.price }


    override fun part2(input: Grid<Char>): Int {
        var groups = generateGroups(input)
        return groups.sumOf { it.discountPrice }
    }

    override fun parse(inputStr: String): Grid<Char> =
        Grid.fromLines(inputStr.lines()) { it }
}