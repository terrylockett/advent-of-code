package ca.terrylockett.aoc2023.day05;

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder;
import java.io.File
import java.io.FileInputStream
import java.util.Collections
import java.util.Scanner
import kotlin.streams.toList

fun main() {
    val inputFile: String = InputFileFinder.getInputFilePath("input.txt").orElseThrow()

    println("day05 2023 part1: ${part1(inputFile)}")
    //println("day05 2023 part2: TODO")
}


fun part1(inputFilePath : String) : Long {
    val inputFile = File(inputFilePath)
    var seedsList = inputFile.readLines().first().split(":")[1].split(" ").filter(String::isNotEmpty).map(String::toLong)
    
    val seedToSoil = getConversionMapFromFile("seed-to-soil", inputFile)
    val soilToFertilizer = getConversionMapFromFile("soil-to-fertilizer", inputFile)
    val fertilizerToWater = getConversionMapFromFile("fertilizer-to-water", inputFile)
    val waterToLight = getConversionMapFromFile("water-to-light", inputFile)
    val lightToTemperature = getConversionMapFromFile("light-to-temperature", inputFile)
    val temperatureToHumidity = getConversionMapFromFile("temperature-to-humidity", inputFile)
    val humidityToLocation = getConversionMapFromFile("humidity-to-location", inputFile)
    
    seedsList = seedsList.stream().map { convertSeed(it, seedToSoil) }.toList()
    seedsList = seedsList.stream().map { convertSeed(it, soilToFertilizer) }.toList()
    seedsList = seedsList.stream().map { convertSeed(it, fertilizerToWater) }.toList()
    seedsList = seedsList.stream().map { convertSeed(it, waterToLight) }.toList()
    seedsList = seedsList.stream().map { convertSeed(it, lightToTemperature) }.toList()
    seedsList = seedsList.stream().map { convertSeed(it, temperatureToHumidity) }.toList()
    seedsList = seedsList.stream().map { convertSeed(it, humidityToLocation) }.toList()
    
    return seedsList.minOf { it }
}


private fun convertSeed(seedValue: Long, conversionMap : List<MapEntry>) : Long {
    for(mapEntry in conversionMap) {
        if(seedValue >= mapEntry.srcRangeStart && seedValue < (mapEntry.srcRangeStart + mapEntry.rangeLength)) {
            val offset = seedValue - mapEntry.srcRangeStart
            return mapEntry.dstRangeStart+offset;
        }
    }
    return  seedValue;
}

private fun getConversionMapFromFile(mapName : String, inputFile : File) : List<MapEntry> {
    val conversionMap = ArrayList<MapEntry>()
    
    val scan = Scanner(FileInputStream(inputFile))
    
    while(scan.hasNextLine()) {
        val line = scan.nextLine()
        if(line.contains(mapName)) {
            var line = scan.nextLine()
            do {
                val tokens = line.split(" ").map(String::toLong)
                conversionMap.add(MapEntry(tokens[0], tokens[1], tokens[2]))
                
                if(!scan.hasNextLine()) break
                line = scan.nextLine()
            } while(line.isNotEmpty())
            break
        }
    }
    
    return conversionMap;
}

class MapEntry(val dstRangeStart: Long, val srcRangeStart: Long, val rangeLength: Long) {
    @Override
    override fun toString(): String {
        return "[dst: $dstRangeStart, src: $srcRangeStart, range: $rangeLength]"
    }
}
