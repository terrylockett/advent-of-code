package ca.terrylockett.aoc2023.day01

import java.io.File
import java.util.regex.Pattern
import kotlin.streams.toList

class CalibrationDocument {
    companion object {
        private val calibrationPattern =
            Pattern.compile("(?=(\\d|one|two|three|four|five|six|seven|eight|nine))")

        private val digitsMap = HashMap<String, Int>()

        init {
            digitsMap["one"] = 1
            digitsMap["two"] = 2
            digitsMap["three"] = 3
            digitsMap["four"] = 4
            digitsMap["five"] = 5
            digitsMap["six"] = 6
            digitsMap["seven"] = 7
            digitsMap["eight"] = 8
            digitsMap["nine"] = 9
        }

        fun basicCalibration(inputFilePath: String): Int {
            var total = 0
            File(inputFilePath).forEachLine { line ->
                val digits =
                    line.chars().filter(Character::isDigit)
                        .boxed().map(Character::getNumericValue)
                        .toList()
                total += (digits.first() * 10) + digits.last()
            }
            return total
        }

        fun fancyCalibration(inputFilePath: String): Int {
            var total = 0
            File(inputFilePath).forEachLine { line ->
                val groups = ArrayList<String>()
                val m = calibrationPattern.matcher(line)
                while (m.find()) {
                    groups.add(m.group(1))
                }

                total += (toDigit(groups.first()) * 10) + toDigit(groups.last())
            }
            return total
        }

        private fun toDigit(matcherVal: String): Int {
            if (digitsMap.containsKey(matcherVal)) {
                return digitsMap.getValue(matcherVal)
            }
            return Integer.parseInt(matcherVal)
        }
    }
}
