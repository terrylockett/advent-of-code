package ca.terrylockett.aoc2023.day10

import java.io.File
import java.lang.Exception
import java.nio.file.Files
import java.util.Optional

//this code is fucked

class PipeMap(inputFilePath : String) {
	private val matrix : Array<CharArray>
	private val startingPoint : Point
	private val startingNode : Node
	
	init {
		matrix = createMap(inputFilePath)
		startingPoint = findStartingPoint(matrix);
		startingNode = Node(startingPoint, matrix[startingPoint.row][startingPoint.col], null)
	}


	
	fun traverse(): Int {
		val resultsList = ArrayList<Int>()
		
		resultsList.add(buildGraphAndGetLength(Direction.DOWN))
		startingNode.downNode = null

		resultsList.add(buildGraphAndGetLength(Direction.LEFT))
		startingNode.leftNode = null

		resultsList.add(buildGraphAndGetLength(Direction.UP))
		startingNode.upNode = null

		resultsList.add(buildGraphAndGetLength(Direction.RIGHT))
		startingNode.rightNode = null
		
		return (resultsList.maxOrNull() ?:0) /2
	}
	
	private fun buildGraphAndGetLength(dir: Direction) : Int {
		val processedNodes = ArrayList<Node>()
		val unprocessedNodes = ArrayDeque<Node>()

		processedNodes.add(startingNode)

		getNextNode(startingNode, dir).ifPresent{ unprocessedNodes.add(it)}

		while(unprocessedNodes.isNotEmpty()) {
			val tmp = unprocessedNodes.removeFirst()
			if(processedNodes.contains(tmp)) {
				continue
			}
			processedNodes.add(tmp)

			getNextNode(tmp, Direction.UP).ifPresent{
				if(!processedNodes.contains(it)) {
					unprocessedNodes.add(it)
				}
			}
			getNextNode(tmp, Direction.DOWN).ifPresent {
				if (!processedNodes.contains(it)) {
					unprocessedNodes.add(it)
				}
			}
			getNextNode(tmp, Direction.LEFT).ifPresent{
				if (!processedNodes.contains(it)) {
					unprocessedNodes.add(it)
				}
			}
			getNextNode(tmp, Direction.RIGHT).ifPresent{
				if (!processedNodes.contains(it)) {
					unprocessedNodes.add(it)
				}
			}
		}

		
		return processedNodes.size
	}
	
	
	private fun getNextNode(node: Node, dir : Direction) : Optional<Node> {
		
		//check if node.value can move in dir
		if (!canNodeMoveInDir(node, dir)) {
			return Optional.empty()
		}
		
		var nextRow = node.point.row
		var nextCol = node.point.col
		when(dir) {
			Direction.UP -> nextRow--
			Direction.DOWN -> nextRow++
			Direction.LEFT -> nextCol--
			Direction.RIGHT -> nextCol++
		}
		
		val newPoint = Point(nextRow, nextCol)
		if(!isInBounds(newPoint)) {
			return Optional.empty()
		}

		val newChar = matrix[newPoint.row][newPoint.col]
		//check if move from node.value to newChar is valid.
		if(!isMoveValid(newChar, dir)) {
			return Optional.empty()
		}


		if(null != node.parent) {
			if(newPoint == node.parent.point) {
				return Optional.empty()
			}
		}
		if(newChar == 'S') {
			when(dir) {
				Direction.UP -> node.upNode = startingNode
				Direction.DOWN -> node.downNode = startingNode
				Direction.LEFT -> node.leftNode = startingNode
				Direction.RIGHT ->node.rightNode = startingNode
			}
			return Optional.empty()
		}
		
		val newNode = Node(newPoint, newChar, node)
		when(dir) {
			Direction.UP -> node.upNode = newNode
			Direction.DOWN -> node.downNode = newNode
			Direction.LEFT -> node.leftNode = newNode
			Direction.RIGHT ->node.rightNode = newNode
		}
		
		return Optional.of(newNode)
	}
	
	private fun isInBounds(point: Point) : Boolean {
		val isRowValid = (point.row >=0 && point.row< matrix.size)
		val isColValid = (point.col >=0 && point.col< matrix[0].size)
		return  isRowValid && isColValid
	}	
}



private fun canNodeMoveInDir(node: Node, dir: Direction): Boolean {
	return  getNextDirsForChar(node.value).contains(dir)
}

private fun isMoveValid(nextChar: Char, dir:Direction): Boolean {
	return (getReceivingCharsForDir(dir).contains(nextChar))
}


class Node(val point: Point, val value: Char, val parent: Node?) {
	var upNode : Node? = null
	var downNode : Node? = null
	var leftNode : Node? = null
	var rightNode : Node? = null

	override fun equals(other: Any?): Boolean {
		if(other !is Node) {
			return false
		}
		return (point == other.point) 
		
	}

	override fun hashCode(): Int {
		return point.hashCode()
	}

}



fun getNextDirsForChar(value: Char) : List<Direction> {
	val list = ArrayList<Direction>()
	when(value) {
		'S' -> {
			list.add(Direction.UP)
			list.add(Direction.LEFT)
			list.add(Direction.DOWN)
			list.add(Direction.RIGHT)
		}
		'|' -> {
			list.add(Direction.UP)
			list.add(Direction.DOWN)
		}
		'-' -> {
			list.add(Direction.LEFT)
			list.add(Direction.RIGHT)
		}
		'L' -> {
			list.add(Direction.UP)
			list.add(Direction.RIGHT)
		}
		'J' -> {
			list.add(Direction.UP)
			list.add(Direction.LEFT)
		}
		'7' -> {
			list.add(Direction.LEFT)
			list.add(Direction.DOWN)
		}
		'F' -> {
			list.add(Direction.DOWN)
			list.add(Direction.RIGHT)
		}
	}

	return list
}

fun getReceivingCharsForDir(direction: Direction) : List<Char>{
	val list = ArrayList<Char>()

	when(direction) {
		Direction.UP -> {
			list.add('|')
			list.add('7')
			list.add('F')
			list.add('S')
		}
		Direction.LEFT -> {
			list.add('-')
			list.add('L')
			list.add('F')
			list.add('S')
		}
		Direction.DOWN -> {
			list.add('|')
			list.add('L')
			list.add('J')
			list.add('S')
		}
		Direction.RIGHT -> {
			list.add('-')
			list.add('J')
			list.add('7')
			list.add('S')
		}
	}

	return list
}




private fun createMap(inputFilePath: String) : Array<CharArray> {
	val inputFile = File(inputFilePath)
	val rows = Files.lines(inputFile.toPath()).count()
	val cols = Files.lines(inputFile.toPath()).findFirst().orElseThrow().length
	val matrix = Array(rows.toInt()) { CharArray(cols) }

	var rowIndex = 0; // this is stinky
	inputFile.forEachLine { line : String ->
		matrix[rowIndex] = line.toCharArray()
		rowIndex++
	}

	return matrix
}

private fun findStartingPoint(matrix : Array<CharArray>) : Point {
	for((rowIndex, row) in matrix.withIndex()) {
		for( (colIndex, item) in row.withIndex()) {
			if(item == 'S') {
				return Point(rowIndex, colIndex)
			}
		}
	}
	throw Exception("alksjdsal")
}

class Point(val row: Int, val col: Int) {
	override fun toString(): String {
		return "[$row, $col]"
	}

	override fun equals(other: Any?): Boolean {
		if(other is Point) {
			return (row == other.row && col == other.col)
		}
		return false;
	}

	override fun hashCode(): Int {
		var result = row
		result = 31 * result + col
		return result
	}
}


enum class Direction {
	UP,
	LEFT,
	DOWN,
	RIGHT
}