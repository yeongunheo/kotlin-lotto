package lotto.domain

class Money(
    private val purchaseFee: Int = 0
) {
    fun getPurchaseFee() = purchaseFee
}