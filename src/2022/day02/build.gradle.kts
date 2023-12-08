plugins {
    `aoc-project-conventions`
}

dependencies {
    implementation(libs.commonsIo)
}

application {
    mainClass.set("ca.terrylockett.aoc2022.day02.Day02Runner")
}
