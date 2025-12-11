package ca.terrylockett.aoc2025.day08;

import ca.terrylockett.aoccommon.input.PuzzleInput;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day08Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	static final Pattern INPUT_VEC_PATTERN = Pattern.compile("(\\d+),(\\d+),(\\d+)");
	public static final int JUNCTIONS = 1000;

	public static void main(String[] args) {
		String input = PuzzleInput.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day08 part1: " + part1(input, JUNCTIONS));
		System.out.println("2025 day08 part2: " + part2(input));
	}

	static int part1(String puzzleInput, int junctions) {
		List<Vector3D> vectors = parseInput(puzzleInput);

		// Get all pair distances
		Map<Double, Pair<Vector3D, Vector3D>> pairDistances = getPairDistances(vectors);

		List<Double> sortedKeys = pairDistances.keySet().stream().sorted().collect(Collectors.toList());

		List<Set<Vector3D>> junctionsList = new ArrayList<>();

		for (int i = 0; i < junctions; i++) {
			Pair<Vector3D, Vector3D> shortestPair = pairDistances.get(sortedKeys.get(i));

			// find if the vectors are already in circuits
			Set<Vector3D> vec1Overlap = null;
			Set<Vector3D> vec2Overlap = null;
			for (var vecSet : junctionsList) {
				if (vecSet.contains(shortestPair.getFirst())) {
					vec1Overlap = vecSet;
				}
				if (vecSet.contains(shortestPair.getSecond())) {
					vec2Overlap = vecSet;
				}
			}

			mergePairIntoCircuits(vec1Overlap, vec2Overlap, shortestPair, junctionsList);
		}

		// finally sort the circuits by size and return the top 3
		List<Integer> junctionNodeCounts = new ArrayList<>();
		for (var junc : junctionsList) {
			junctionNodeCounts.add(junc.size());
		}

		junctionNodeCounts.sort(Collections.reverseOrder());
		return junctionNodeCounts.get(0) * junctionNodeCounts.get(1) * junctionNodeCounts.get(2);
	}

	static int part2(String puzzleInput) {
		List<Vector3D> vectors = parseInput(puzzleInput);

		// Get all pair distances,
		Map<Double, Pair<Vector3D, Vector3D>> pairDistances = getPairDistances(vectors);

		List<Double> sortedKeys = pairDistances.keySet().stream().sorted().collect(Collectors.toList());

		List<Set<Vector3D>> junctionsList = new ArrayList<>();

		while (true) {
			Pair<Vector3D, Vector3D> shortestPair = pairDistances.get(sortedKeys.remove(0));

			// find if the vectors are already in circuits
			Set<Vector3D> vec1Overlap = null;
			Set<Vector3D> vec2Overlap = null;
			for (var vecSet : junctionsList) {
				if (vecSet.contains(shortestPair.getFirst())) {
					vec1Overlap = vecSet;
				}
				if (vecSet.contains(shortestPair.getSecond())) {
					vec2Overlap = vecSet;
				}
			}

			mergePairIntoCircuits(vec1Overlap, vec2Overlap, shortestPair, junctionsList);

			// check if all nodes are in one circuit.
			if (junctionsList.size() == 1) {
				if (junctionsList.get(0).size() == vectors.size()) {
					double s = shortestPair.getFirst().getX() * shortestPair.getSecond().getX();
					return (int) s;
				}
			}
		}
	}

	private static void mergePairIntoCircuits(Set<Vector3D> vec1Overlap, Set<Vector3D> vec2Overlap,
			Pair<Vector3D, Vector3D> pair, List<Set<Vector3D>> junctionsList) {
		// if both v1 & v2 not in junctionsList then add new set containing the two vecs
		if (null == vec1Overlap && null == vec2Overlap) {
			Set<Vector3D> newSet = new HashSet<>(Set.of(pair.getFirst(), pair.getSecond()));
			junctionsList.add(newSet);
		}

		// If only v1 in junctions, then add V2 to the set
		else if (null != vec1Overlap && null == vec2Overlap) {
			vec1Overlap.add(pair.getSecond());
		}

		// If only v2 in junctions then add V1 to the set
		else if (null == vec1Overlap && null != vec2Overlap) {
			vec2Overlap.add(pair.getFirst());
		}

		// If v1 and v2 are in junctionList, then merge both destination sets to one set
		else {
			Set<Vector3D> newSet = new HashSet<>();
			newSet.addAll(vec1Overlap);
			newSet.addAll(vec2Overlap);
			junctionsList.remove(vec1Overlap);
			junctionsList.remove(vec2Overlap);
			junctionsList.add(newSet);
		}
	}

	@NotNull
	private static Map<Double, Pair<Vector3D, Vector3D>> getPairDistances(List<Vector3D> vectors) {
		Map<Double, Pair<Vector3D, Vector3D>> pairDistances = new HashMap<>();
		for (int i = 0; i < vectors.size(); i++) {
			for (int k = i + 1; k < vectors.size(); k++) {
				Vector3D vec1 = vectors.get(i);
				Vector3D vec2 = vectors.get(k);

				double distance = vec1.distance(vec2);
				pairDistances.put(distance, new Pair<>(vec1, vec2));
			}
		}
		return pairDistances;
	}

	static List<Vector3D> parseInput(String puzzleInput) {
		List<Vector3D> items = new ArrayList<>();
		var lines = puzzleInput.lines().collect(Collectors.toList());
		for (String line : lines) {
			Matcher m = INPUT_VEC_PATTERN.matcher(line);
			if (!m.find()) {
				throw new RuntimeException("Error parsing input. rgex didnt match.");
			}
			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			int z = Integer.parseInt(m.group(3));

			items.add(new Vector3D(x, y, z));
		}
		return items;
	}
}
