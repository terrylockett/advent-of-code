# Advent of Code

The Most beautiful kotlin and java code ever written for [AOC](https://adventofcode.com)

<img src="https://terrylockettca.s3.us-east-2.amazonaws.com/images/aoc2023.png" width="400" style="border-radius: 5px" alt="Cool pic of AOC holding a piece of paper">

Guaranteed I will finish all days.


## Configuration
`JDK 11+`

Set AOC session token in your `~/.gradle/gradle.properties` to download the puzzle input.
```
AOC_TOKEN=abc123
```

<details>
<summary>How to get session token.</summary>

1. Authenticate at [https://adventofcode.com](https://adventofcode.com).
2. Open Web dev tools(cmd + opt + i) -> Storage tab
3. Copy `session` value
</details>

## Usage

#### Build
`./gradlew build`

#### Run
`./gradlew 2023:day01:run`

#### Lint
`./gradlew 2023:day01:spotlessApply`

#### Test
`./gradlew 2023:day01test`

#### Benchmark
`./gradlew 2024:day03:jmh`

#### Create new Module
`./gradlew newModule -PmoduleName=2023:day02 -Plang=kotlin`\
valid langs = [kotlin | java]

#### Download puzzle input
`./gradlew 2023:day01:downloadPuzzleInput`

#### Clean puzzle input
`./gradlew 2023:day01:cleanPuzzleInput`\
Note: `clean` does NOT depend on this.
