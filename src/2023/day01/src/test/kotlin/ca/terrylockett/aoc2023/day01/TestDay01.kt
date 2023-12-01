package ca.terrylockett.aoc2023.day01

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay01 {

  companion object {
    const val TEST_FILE_NAME = "test-input.txt"
  }

  private var testFilePath = ""

  @BeforeAll
  fun setup() {
    testFilePath =
        InputFileFinder.getFilePath(TestDay01::class.java.getClassLoader(), TEST_FILE_NAME)
  }

  @Test
  fun testDay01() {
    assertTrue(true)
  }
}
