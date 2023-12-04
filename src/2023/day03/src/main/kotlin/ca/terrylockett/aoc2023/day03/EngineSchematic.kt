package ca.terrylockett.aoc2023.day03

import java.io.File
import java.nio.file.Files

class PartNumber(val point: Point, val length: Int, val value: Int)

class Point(val row: Int, val col: Int)

class EngineSchematic(private val inputFilePath: String) {
    private var matrix: Array<CharArray>
    private val partNumberList: List<PartNumber>

    init {
        val inputFile = File(inputFilePath)
        val rows = Files.lines(inputFile.toPath()).count()
        val cols = Files.lines(inputFile.toPath()).findFirst().orElseThrow().length

        matrix = Array(rows.toInt()) { CharArray(cols) }

        var rowIndex = 0; // this is stinky
        inputFile.forEachLine { line: String ->
            matrix[rowIndex] = line.toCharArray()
            rowIndex++
        }

        this.partNumberList = createPartsNumberList()
    }

    fun part1FindParts(): Int {
        var total = 0

        for (partNumber in partNumberList) {
            if (isPartNumberValid(partNumber)) {
                total += partNumber.value
            }
        }

        return total
    }

    fun part2FindGears(): Int {
        var total = 0

        for ((rowIndex, rowArr) in matrix.withIndex()) {
            for ((colIndex, charValue) in rowArr.withIndex()) {
                if (charValue.isDigit() || charValue == '.') {
                    continue
                }
                total += checkIsGear(rowIndex, colIndex)
            }
        }

        return total
    }

    private fun isPartNumberValid(partNumber: PartNumber): Boolean {
        val point = partNumber.point

        for (row in point.row - 1..point.row + 1) {
            for (col in point.col - 1..point.col + partNumber.length) {
                val isInBounds: Boolean = (row >= 0 && row < matrix.size) && (col >= 0 && col < matrix[0].size)
                if (!isInBounds) {
                    continue
                }
                if (!matrix[row][col].isDigit() && matrix[row][col] != '.') {
                    return true
                }
            }
        }
        return false
    }

    private fun checkIsGear(
        row: Int,
        col: Int,
    ): Int {
        val overlappingPartNumbers = ArrayList<PartNumber>()

        for (partNumber in partNumberList) {
            if (row !in partNumber.point.row - 1..partNumber.point.row + 1) {
                continue
            }
            if (col !in partNumber.point.col - 1..partNumber.point.col + partNumber.length) {
                continue
            }

            overlappingPartNumbers.add(partNumber)
        }

        if (overlappingPartNumbers.size == 2) {
            return overlappingPartNumbers[0].value * overlappingPartNumbers[1].value
        }
        return 0
    }

    private fun createPartsNumberList(): List<PartNumber> {
        // this function is filthy
        val numbersList = ArrayList<PartNumber>()

        for ((rowIndex, rowArr) in matrix.withIndex()) {
            var colIndex = 0
            while (colIndex < rowArr.size) {
                val colValue = rowArr[colIndex]
                if (colValue.isDigit()) {
                    var startIndex = colIndex + 1
                    while (startIndex < rowArr.size) {
                        if (rowArr[startIndex].isDigit()) {
                            startIndex++
                            continue
                        }
                        break
                    }
                    val value: Int = Integer.parseInt(rowArr.concatToString().subSequence(colIndex, startIndex).toString())
                    numbersList.add(PartNumber(Point(rowIndex, colIndex), startIndex - colIndex, value))

                    colIndex = startIndex
                }
                colIndex++
            }
        }
        return numbersList
    }
}
