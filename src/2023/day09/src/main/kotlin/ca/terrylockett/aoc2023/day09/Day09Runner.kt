package ca.terrylockett.aoc2023.day09

import ca.terrylockett.aoccommon.resources.Resources
import java.io.File

class Report(private val numbers: List<Int>) {
	
	fun findNextElement(): Int {
		return numbers.last() + calcNextElement(numbers)
	}
	
	fun findPreviousElement(): Int {
		return numbers.first() + calcNextElement(numbers.reversed())
	}
	
	private fun calcNextElement(numbers: List<Int>): Int {
		val diffList = ArrayList<Int>()
		for (i in 0..numbers.size - 2) {
			val diff = numbers[i + 1] - numbers[i]
			diffList.add(diff)
		}

		if (diffList.stream().allMatch { it == 0 }) {
			return diffList.last()
		}

		return diffList.last() + calcNextElement(diffList)
	}
	
}

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()
	val reports = createReports(inputFile)
	println("2023 day09 part1: ${part1(reports)}")
	println("2023 day09 part2: ${part2(reports)}")
}

fun part1(reports: List<Report>): Int {
	var total = 0
	for (report in reports) {
		total += report.findNextElement()
	}
	return total
}

fun part2(reports: List<Report>): Int {
	var total = 0
	for (report in reports) {
		total += report.findPreviousElement()
	}
	return total
}

fun createReports(inputFilePath: String): ArrayList<Report> {
	val reports = ArrayList<Report>()
	
	File(inputFilePath).forEachLine { line ->
		val report = Report(line.split(" ").map(Integer::parseInt).toList())
		reports.add(report)
	}
	
	return reports
}
