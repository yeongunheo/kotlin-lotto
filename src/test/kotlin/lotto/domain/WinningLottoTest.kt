package lotto.domain

import lotto.domain.numbers.LottoNumber
import lotto.domain.prize.LottoPrize
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `당첨 번호는 사용자가 입력한 숫자로 이루어져있다`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val winningLotto = WinningLotto(winningNumbers, 7)

        assertThat(winningLotto.winningNumbers.list).isEqualTo(winningNumbers.map { LottoNumber(it) })
    }

    @Test
    fun `보너스 볼 번호를 저장해야 한다`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 7
        val winningLotto = WinningLotto(winningNumbers, bonusBallNumber)

        assertThat(winningLotto.bonusNumber).isEqualTo(LottoNumber(bonusBallNumber))
    }

    @Test
    fun `보너스 볼 번호와 당첨 번호가 겹치면 IllegalArgumentsException 이 발생한다`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 10

        assertThatIllegalArgumentException().isThrownBy { WinningLotto(winningNumbers, bonusBallNumber) }
    }

    @Test
    fun `보너스 볼 번호와 겹치는지 알 수 있다`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 1
        val winningLotto = WinningLotto(winningNumbers, bonusBallNumber)

        val lottoWithBonusNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val lottoWithoutBonusNumber = Lotto(listOf(2, 3, 4, 5, 6, 7))

        assertTrue(winningLotto.matchesBonus(lottoWithBonusNumber))
        assertFalse(winningLotto.matchesBonus(lottoWithoutBonusNumber))
    }

    @Test
    fun `각 로또마다 당첨번호와 몇 개 겹치는지 계산할 수 있다`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val winningLotto = WinningLotto(winningNumbers, 7)

        val threeMatchingNumbers = listOf(10, 20, 30, 1, 2, 3)
        val candidateLotto1 = Lotto(threeMatchingNumbers)

        assertThat(winningLotto.getNumberOfMatchingNumbers(candidateLotto1)).isEqualTo(3)
    }

    @Test
    fun `당첨 번호와 순서가 달라도 몇 개 겹치는지 계산할 수 있다`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val winningLotto = WinningLotto(winningNumbers, 7)

        val sixMatchingNumbers = listOf(40, 35, 10, 45, 20, 30)
        val candidateLotto2 = Lotto(sixMatchingNumbers)

        assertThat(winningLotto.getNumberOfMatchingNumbers(candidateLotto2)).isEqualTo(6)
    }

    @Test
    fun `당첨 번호와 보너스 번호가 겹치는지에 따라 그에 맞는 LottoPrize 를 반환할 수 있다`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 1
        val winningLotto = WinningLotto(winningNumbers, bonusBallNumber)

        val lottoWithSecondPrize = Lotto(listOf(10, 20, 30, 35, 40, 1))

        assertThat(winningLotto.getLottoPrizeOf(lottoWithSecondPrize)).isEqualTo(LottoPrize.SECOND)
    }
}