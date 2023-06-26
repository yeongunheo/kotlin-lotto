package lotto.view

import lotto.domain.LottoRank
import lotto.domain.LottosResult
import lotto.domain.response.LottosGenerateResponse

object LottoOutputView {
    fun printLottos(response: LottosGenerateResponse) {
        println("수동으로 ${response.manualLottos.lottoQuantity}장, 자동으로 ${response.autoLottos.lottoQuantity}개를 구매했습니다.")
        response.lottos.values.forEach { lotto ->
            println("[${lotto.numbers.values.sortedBy { it.value }.joinToString(", ")}]")
        }
        println()
    }

    fun printLottoResults(lottosResult: LottosResult) {
        println("당첨 통계")
        println("---------")
        lottosResult.winningResults.forEach { (lottoRank) ->
            printLottoRank(lottoRank, lottosResult.getWinningResultsCount(lottoRank))
        }
        println("수익률은 ${lottosResult.returnOfRate}입니다.")
    }

    private fun printLottoRank(lottoRank: LottoRank, winningResultsCount: Int) {
        if (lottoRank == LottoRank.SECOND) {
            println("${lottoRank.matchCount}개 일치, 보너스 볼 일치 (${lottoRank.winningMoney}) - ${winningResultsCount}개 ")
            return
        }
        println("${lottoRank.matchCount}개 일치 (${lottoRank.winningMoney}) - ${winningResultsCount}개")
    }
}