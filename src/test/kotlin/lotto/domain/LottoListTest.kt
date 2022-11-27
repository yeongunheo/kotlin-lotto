package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LottoListTest {

    private val numberGenerator: NumberGenerator = ManualNumberGenerator()

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun compare() {
        val lottoNumbers = "1,2,3,4,5,6"
        val winningLotto = LottoGenerator.generateLotto(lottoNumbers)
        val lottoNumber = 7
        val bonusLottoNumber = LottoNumber(lottoNumber)

        val lottoCount = 4L
        val lottoList = LottoGenerator.generateLottoList(lottoCount, numberGenerator)

        val lottoRankList = LottoList(lottoList).compare(winningLotto, bonusLottoNumber)

        assertThat(lottoRankList.count().toLong()).isEqualTo(lottoCount)
        lottoRankList.forEach { lottoRank ->
            assertThat(lottoRank).isEqualTo(LottoRank.FIRST_PLACE)
        }
    }

    @Test
    fun `compare return empty`() {
        val lottoNumbers = "4,5,9,10,11,12"
        val winningLotto = LottoGenerator.generateLotto(lottoNumbers)
        val lottoNumber = 6
        val bonusLottoNumber = LottoNumber(lottoNumber)

        val lottoCount = 4L
        val lottoList = LottoGenerator.generateLottoList(lottoCount, numberGenerator)

        val lottoRankList = LottoList(lottoList).compare(winningLotto, bonusLottoNumber)

        assertThat(lottoRankList).isEmpty()
    }
}