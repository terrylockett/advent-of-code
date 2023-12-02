package ca.terrylockett.aoc2023.day02

import java.io.File

class CubeGame {
    companion object {
        private val part1Map = HashMap<String, Int>()

        init {
            part1Map["red"] = 12
            part1Map["green"] = 13
            part1Map["blue"] = 14
        }

        fun playGamePart1(inputFilePath: String): Int {
            var total = 0

            File(inputFilePath).forEachLine { line: String ->
                total += playPart1Line(line)
            }
            return total
        }

        private fun playPart1Line(line: String): Int {
            val tokens = line.split(":", ";")
            val round = tokens[0].split(" ")[1]

            val cubeSets = tokens.subList(1, tokens.size).map(String::trim)

            val cubes = ArrayList<String>()

            cubeSets.forEach {
                it.split(",").map(String::trim).forEach(cubes::add)
            }

            cubes.forEach { cube: String ->
                val cubeTokes = cube.split(" ")
                val count = Integer.parseInt(cubeTokes[0])
                val color = cubeTokes[1]

                if (count > part1Map.getValue(color)) {
                    return 0
                }
            }

            return Integer.parseInt(round)
        }
    }
}
