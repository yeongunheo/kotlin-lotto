package lotto.domain

import kotlin.math.floor

class LottoResultCalculator(private val winningNumbers: Lotto) {

    fun calculateResult(lottos: List<Lotto>): Map<Rank, Int> {
        val result = mutableMapOf<Rank, Int>()
        for (lotto in lottos) {
            val matchCount = winningNumbers.matchCount(lotto)
            val rank = Rank.from(matchCount, false)
            val lottoCount = result.getOrDefault(rank, 0)
            result.putIfAbsent(rank, lottoCount + 1)
        }
        return result
    }

    fun calculateEarningRate(result: Map<Rank, Int>, amount: Int): Double {
        val rate = result.map { result.getOrDefault(it.key, 0) * it.key.winningMoney }.sumOf { it.toDouble() }.div(amount)
        return floor(rate * 100) / 100
    }
}
