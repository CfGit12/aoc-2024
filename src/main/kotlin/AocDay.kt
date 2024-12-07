import kotlin.system.measureNanoTime

abstract class AocDay<T>(private val day: Int) {

    private val sampleInput: T = parse(readFile("$day-sample.txt"))
    private val input: T = parse(readFile("$day.txt"))

    abstract fun part1(input: T): Any
    abstract fun part2(input: T): Any
    abstract fun parse(inputStr: String): T

    fun execute() {
        println("--- DAY $day ---")
        val a: Any
        val aTime = measureNanoTime {
            a = part1(sampleInput)
        } / 1_000_000.00
        println("Part 1 Sample: $a ($aTime ms)")

        val b: Any
        val bTime = measureNanoTime {
            b = part2(sampleInput)
        } / 1_000_000.00
        println("Part 2 Sample: $b ($bTime ms)")

        val c: Any
        val cTime = measureNanoTime {
            c = part1(input)
        } / 1_000_000.00
        println("Part 1: $c ($cTime ms)")

        val d: Any
        val dTime = measureNanoTime {
            d = part2(input)
        } / 1_000_000.00
        println("Part 2: $d ($dTime ms)")
    }
}