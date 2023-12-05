package ca.terrylockett.aoc2023.day05

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import java.io.File
import java.io.FileInputStream
import java.util.Scanner
import kotlin.streams.toList

class MapEntry(val dstRangeStart: Long, val srcRangeStart: Long, val rangeLength: Long) {
    @Override
    override fun toString(): String {
        return "[dst: $dstRangeStart, src: $srcRangeStart, range: $rangeLength]"
    }
}

fun main() {
    val inputFile: String = InputFileFinder.getInputFilePath("input.txt").orElseThrow()

    println("day05 2023 part1: ${part1(inputFile)}")
    println("day05 2023 part2: ${part2(inputFile)}")
}

lateinit var seedToSoil: List<MapEntry>
lateinit var soilToFertilizer: List<MapEntry>
lateinit var fertilizerToWater: List<MapEntry>
lateinit var waterToLight: List<MapEntry>
lateinit var lightToTemperature: List<MapEntry>
lateinit var temperatureToHumidity: List<MapEntry>
lateinit var humidityToLocation: List<MapEntry>

fun part1(inputFilePath: String): Long {
    val inputFile = File(inputFilePath)
    initConversionMaps(inputFile)
    val seedsList = inputFile.readLines().first().split(":")[1].split(" ").filter(String::isNotEmpty).map(String::toLong)

    return convertSeedsList(seedsList)
}

fun part2(inputFilePath: String): Long {
    val inputFile = File(inputFilePath)
    initConversionMaps(inputFile)
    val seedsList = inputFile.readLines().first().split(":")[1].split(" ").filter(String::isNotEmpty).map(String::toLong)

    var minValue = Long.MAX_VALUE

    val iter = seedsList.iterator()
    while (iter.hasNext()) {
        val seed1 = iter.next()
        val seed2 = iter.next()

        for (i in seed1..<seed1 + seed2) {
            var tmp = convertSeed(i, seedToSoil)
            tmp = convertSeed(tmp, soilToFertilizer)
            tmp = convertSeed(tmp, fertilizerToWater)
            tmp = convertSeed(tmp, waterToLight)
            tmp = convertSeed(tmp, lightToTemperature)
            tmp = convertSeed(tmp, temperatureToHumidity)
            tmp = convertSeed(tmp, humidityToLocation)

            if (tmp < minValue) {
                minValue = tmp
            }
        }
    }

    return minValue
}

private fun convertSeedsList(seedsToProcess: List<Long>): Long {
    var processList = seedsToProcess.stream().map { convertSeed(it, seedToSoil) }.toList()
    processList = processList.stream().map { convertSeed(it, soilToFertilizer) }.toList()
    processList = processList.stream().map { convertSeed(it, fertilizerToWater) }.toList()
    processList = processList.stream().map { convertSeed(it, waterToLight) }.toList()
    processList = processList.stream().map { convertSeed(it, lightToTemperature) }.toList()
    processList = processList.stream().map { convertSeed(it, temperatureToHumidity) }.toList()
    processList = processList.stream().map { convertSeed(it, humidityToLocation) }.toList()

    return processList.minOf { it }
}

private fun convertSeed(
    seedValue: Long,
    conversionMap: List<MapEntry>,
): Long {
    for (mapEntry in conversionMap) {
        if (seedValue >= mapEntry.srcRangeStart && seedValue < (mapEntry.srcRangeStart + mapEntry.rangeLength)) {
            val offset = seedValue - mapEntry.srcRangeStart
            return mapEntry.dstRangeStart + offset
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


