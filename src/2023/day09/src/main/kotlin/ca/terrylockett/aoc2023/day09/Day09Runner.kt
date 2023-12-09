package ca.terrylockett.aoc2023.day09

import ca.terrylockett.aoccommon.resources.Resources
import java.io.File

typealias Report = List<Int>

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()
	println("2023 day09 part1: ${part1(inputFile)}")
	println("2023 day09 part2: ${part2(inputFile)}")
}

fun part1(inputFilePath: String): Int {
	val reports = createReports(inputFilePath)
	
	var total = 0
	for (report in reports) {
		val nextToken = report.last() + calcNextElement(report)
		total += nextToken
	}

	return total
}

fun part2(inputFilePath: String): Int {
	val reports = createReports(inputFilePath)
	
	var total = 0
	for (report in reports) {
		val nextToken = report.first() - calcPreviousElement(report)
		total += nextToken
	}

	return total
}

fun createReports(inputFilePath: String): ArrayList<Report> {
	val reports = ArrayList<Report>()

	File(inputFilePath).forEachLine { line ->
		val report: Report = line.split(" ").map(Integer::parseInt).toList()
		reports.add(report)
	}

	return reports
}

fun calcNextElement(report: Report): Int {
	val diffList = ArrayList<Int>()

	for (i in 0..report.size - 2) {
		val diff = report[i + 1] - report[i]
		diffList.add(diff)
	}

	if (diffList.stream().allMatch { it == 0 }) {
		return diffList.last()
	}

	return diffList.last() + calcNextElement(diffList)
}

fun calcPreviousElement(report: Report): Int {
	val diffList = ArrayList<Int>()

	for (i in 0..report.size - 2) {
		val diff = report[i + 1] - report[i]
		diffList.add(diff)
	}

	if (diffList.stream().allMatch { it == 0 }) {
		return diffList.first()
	}

	return diffList.first() - calcPreviousElement(diffList)
}
