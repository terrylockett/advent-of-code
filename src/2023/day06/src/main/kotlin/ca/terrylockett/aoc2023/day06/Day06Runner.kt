package ca.terrylockett.aoc2023.day06

import ca.terrylockett.aoccommon.inputfilefinder.Resources
import java.io.File
import java.util.stream.Collectors

class Race(val time: Long, val record: Long)

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()

	println("2023 day06 part1: ${part1(inputFile)}")
	println("2023 day06 part2: ${part2(inputFile)}")
}

fun part1(inputFilePath: String): Int {
	val races = createPart1Races(inputFilePath)

	var total = 1
	for (race in races) {
		total *= getNumOfWinningInputs(race)
	}
	return total
}

fun part2(inputFilePath: String): Int {
	val race = createPart2Race(inputFilePath)
	return getNumOfWinningInputs(race)
}

private fun getNumOfWinningInputs(race: Race): Int {
	var winningInputCount = 0
	for (timeHoldingButton in 0..race.time) {
		val distance = timeHoldingButton * (race.time - timeHoldingButton)
		if (distance > race.record) {
			winningInputCount++
		}
	}
	return winningInputCount
}

private fun createPart1Races(inputFilePath: String): List<Race> {
	val lines = File(inputFilePath).readLines()

	val timeTokens =
		lines[0]
			.split(":")[1]
			.split(Regex("\\s+"))
			.filter(String::isNotEmpty)
			.map(String::toLong)

	val recordTokens =
		lines[1]
			.split(":")[1]
			.split(Regex("\\s+"))
			.filter(String::isNotEmpty)
			.map(String::toLong)

	val races = ArrayList<Race>()

	for (i in timeTokens.indices) {
		races.add(Race(timeTokens[i], recordTokens[i]))
	}

	return races
}

private fun createPart2Race(inputFilePath: String): Race {
	val lines = File(inputFilePath).readLines()

	val time =
		lines[0]
			.split(":")[1]
			.split(Regex("\\s+"))
			.filter(String::isNotEmpty)
			.stream()
			.collect(Collectors.joining())

	val record =
		lines[1]
			.split(":")[1]
			.split(Regex("\\s+"))
			.filter(String::isNotEmpty)
			.stream()
			.collect(Collectors.joining())

	return Race(time.toLong(), record.toLong())
}
