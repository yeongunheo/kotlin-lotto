package lotto.domain

import lotto.Const

class LottoTicket(
    private val value: Set<LottoNumber>
) {
    constructor(lottoNumbers: List<Int>) : this(lottoNumbers.map { LottoNumber(it) }.toSet())

    init {
        require(value.size == LOTTO_NUMBER_COUNT) { Const.ErrorMsg.LOTTO_TICKET_NUMBER_IS_NOT_6_ERROR_MSG }
    }

    fun countIntersection(other: LottoTicket): Int =
        this.value.intersect(other.value).size

    fun toSortedList(): List<LottoNumber> =
        value.sortedBy { it }

    operator fun contains(number: LottoNumber) = value.any { it == number }

    fun toLottoPrize(winningLotto: WinningLotto): LottoPrize? {
        val count = countIntersection(winningLotto.winningTicket)
        val bonusMatch = winningLotto.bonusNumber in this

        return when (count) {
            LottoPrize.THIRD.matchCount -> {
                if (bonusMatch) LottoPrize.SECOND
                else LottoPrize.THIRD
            }
            else -> {
                LottoPrize.values().firstOrNull() { it.matchCount == count }
            }
        }
    }

    companion object {
        const val LOTTO_NUMBER_COUNT = 6

        fun new(): LottoTicket {
            val lottoNumbers = (LottoNumber.MIN_LOTTO_NUM..LottoNumber.MAX_LOTTO_NUM)
                .shuffled()
                .take(6)
            return LottoTicket(lottoNumbers)
        }
    }
}