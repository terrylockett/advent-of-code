package ca.terrylockett.aoc2023.day01

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import org.junit.jupiter.api.Assertions.assertEquals
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
    testFilePath = InputFileFinder.getInputFilePath(TEST_FILE_NAME).orElseThrow()
  }

  @Test
  fun testDay01() {
    val document = CalibrationDocument(testFilePath)

    assertEquals(142, document.calibrationValuesSum())
  }
}
