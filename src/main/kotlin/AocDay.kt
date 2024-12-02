abstract class AocDay<T>(private val day: Int) {

    private val input: T = parse(readFile("$day.txt"))
    private val sampleInput: T = parse(readFile("$day-sample.txt"))

    abstract fun part1(input: T): Any
    abstract fun part2(input: T): Any
    abstract fun parse(inputStr: String): T

    fun execute() {
        println("--- DAY $day ---")
        println("Part 1 Sample: ${part1(sampleInput)}")
        println("Part 2 Sample: ${part2(sampleInput)}")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }
}