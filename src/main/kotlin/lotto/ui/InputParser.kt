package lotto.ui

import lotto.Lotto

object InputParser {
    private const val DELIMITER = ","

    fun parse(numbers: String): Lotto {
        return Lotto(numbers.split(DELIMITER).map { it.trim().toInt() }.toSet())
    }
}