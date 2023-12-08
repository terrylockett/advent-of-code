plugins {
    `aoc-project-conventions`
}

dependencies {
    implementation(libs.guava)
}

application {
    mainClass.set("ca.terrylockett.aoc2022.day01.Day01Runner")
}
