package ca.terrylockett.aoc2023.day02

import ca.terrylockett.aoc2023.day02.Color.BLUE
import ca.terrylockett.aoc2023.day02.Color.GREEN
import ca.terrylockett.aoc2023.day02.Color.RED
import java.nio.file.Files
import java.nio.file.Path

class Cube(val count: Int, val color: Color)

enum class Color(val color: String) {
    BLUE("blue"),
    GREEN("green"),
    RED("red"),
    ;

    companion object {
        private val map = entries.associateBy { it.color }

        infix fun from(color: String) = map.getValue(color)
    }
}

class CubeGame {
    companion object {
        private val part1Map = HashMap<Color, Int>()

        init {
            part1Map[RED] = 12
            part1Map[GREEN] = 13
            part1Map[BLUE] = 14
        }

        fun playGamePart1(inputFilePath: String): Int {
            return Files.lines(Path.of(inputFilePath))
                .mapToInt(CubeGame::playPart1Line)
                .sum()
        }

        fun playGamePart2(inputFilePath: String): Int {
            return Files.lines(Path.of(inputFilePath))
                .mapToInt(CubeGame::playPart2Line)
                .sum()
        }

        private fun playPart1Line(line: String): Int {
            val roundId = Integer.parseInt(line.split(":")[0].split(" ")[1])

            getCubesForLine(line).forEach { cube: Cube ->
                if (cube.count > part1Map.getValue(cube.color)) {
                    return 0
                }
            }

            return roundId
        }

        private fun playPart2Line(line: String): Int {
            var blueMin = 1
            var redMin = 1
            var greenMin = 1

            getCubesForLine(line).forEach { cube: Cube ->
                when (cube.color) {
                    RED -> if (cube.count > redMin) redMin = cube.count
                    GREEN -> if (cube.count > greenMin) greenMin = cube.count
                    BLUE -> if (cube.count > blueMin) blueMin = cube.count
                }
            }

            return blueMin * greenMin * redMin
        }

        private fun getCubesForLine(line: String): List<Cube> {
            val tokens = line.split(":", ";")

            val cubeSets = tokens.subList(1, tokens.size).map(String::trim)

            val cubes = ArrayList<Cube>()

            cubeSets.forEach { it ->
                it.split(",").map(String::trim).forEach {
                    val cubeTokes = it.split(" ")
                    val count = Integer.parseInt(cubeTokes[0])
                    val color = cubeTokes[1]
                    cubes.add(Cube(count, Color.from(color)))
                }
            }

            return cubes
        }
    }
}
