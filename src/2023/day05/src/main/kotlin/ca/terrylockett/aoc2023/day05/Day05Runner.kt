package ca.terrylockett.aoc2023.day05

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import java.io.File
import java.io.FileInputStream
import java.util.Scanner

lateinit var seedToSoil: List<MapEntry>
lateinit var soilToFertilizer: List<MapEntry>
lateinit var fertilizerToWater: List<MapEntry>
lateinit var waterToLight: List<MapEntry>
lateinit var lightToTemperature: List<MapEntry>
lateinit var temperatureToHumidity: List<MapEntry>
lateinit var humidityToLocation: List<MapEntry>

class MapEntry(val dstRangeStart: Long, val srcRangeStart: Long, rangeLength: Long) {
    val srcRangEnd: Long = srcRangeStart + rangeLength
}

fun main() {
    val inputFile: String = InputFileFinder.getInputFilePath("input.txt").orElseThrow()

    println("day05 2023 part1: ${part1(inputFile)}")
    println("day05 2023 part2: ${part2(inputFile)}")
}

fun part1(inputFilePath: String): Long {
    val inputFile = File(inputFilePath)
    initConversionMaps(inputFile)
    val seedsList = inputFile.readLines().first().split(":")[1].split(" ").filter(String::isNotEmpty).map(String::toLong)

    var minValue = Long.MAX_VALUE

    for (seed in seedsList) {
        val location = convertSeedToLocation(seed)
        if (location < minValue) {
            minValue = location
        }
    }

    return minValue
}

fun part2(inputFilePath: String): Long {
    val inputFile = File(inputFilePath)
    initConversionMaps(inputFile)
    val seedsList = inputFile.readLines().first().split(":")[1].split(" ").filter(String::isNotEmpty).map(String::toLong)

    var minValue = Long.MAX_VALUE

    val iter = seedsList.iterator()
    while (iter.hasNext()) {
        val rangeStart = iter.next()
        val rangeLength = iter.next()

        for (seed in rangeStart..<rangeStart + rangeLength) {
            val location = convertSeedToLocation(seed)

            if (location < minValue) {
                minValue = location
            }
        }
    }

    return minValue
}

private fun convertSeedToLocation(seed: Long): Long {
    var tmp = convertValue(seed, seedToSoil)
    tmp = convertValue(tmp, soilToFertilizer)
    tmp = convertValue(tmp, fertilizerToWater)
    tmp = convertValue(tmp, waterToLight)
    tmp = convertValue(tmp, lightToTemperature)
    tmp = convertValue(tmp, temperatureToHumidity)
    tmp = convertValue(tmp, humidityToLocation)

    return tmp
}

private fun convertValue(
    seedValue: Long,
    conversionMap: List<MapEntry>,
): Long {
    for (map in conversionMap) {
        if (seedValue >= map.srcRangeStart && seedValue < map.srcRangEnd) {
            val offset = seedValue - map.srcRangeStart
            return map.dstRangeStart + offset
        }
    }
    return seedValue
}

private fun getConversionMapFromFile(
    mapName: String,
    inputFile: File,
): List<MapEntry> {
    val conversionMap = ArrayList<MapEntry>()

    val scan = Scanner(FileInputStream(inputFile))

    while (scan.hasNextLine()) {
        val line = scan.nextLine()
        if (line.contains(mapName)) {
            var line = scan.nextLine()
            do {
                val tokens = line.split(" ").map(String::toLong)
                conversionMap.add(MapEntry(tokens[0], tokens[1], tokens[2]))

                if (!scan.hasNextLine()) break
                line = scan.nextLine()
            } while (line.isNotEmpty())
            break
        }
    }

    return conversionMap
}

fun initConversionMaps(inputFile: File) {
    seedToSoil = getConversionMapFromFile("seed-to-soil", inputFile)
    soilToFertilizer = getConversionMapFromFile("soil-to-fertilizer", inputFile)
    fertilizerToWater = getConversionMapFromFile("fertilizer-to-water", inputFile)
    waterToLight = getConversionMapFromFile("water-to-light", inputFile)
    lightToTemperature = getConversionMapFromFile("light-to-temperature", inputFile)
    temperatureToHumidity = getConversionMapFromFile("temperature-to-humidity", inputFile)
    humidityToLocation = getConversionMapFromFile("humidity-to-location", inputFile)
}
