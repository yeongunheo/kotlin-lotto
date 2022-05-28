package lotto.dto

data class InputPaymentRequestDto(
    val payment: Int
) {

    companion object {

        const val CANNOT_CONVERT_INT = "정수로 변환할 수 없습니다."

        fun convertPayment(inputPayment: String): InputPaymentRequestDto {
            val payment: Int = inputPayment.toIntOrNull() ?: throw IllegalArgumentException(CANNOT_CONVERT_INT)
            return InputPaymentRequestDto(payment)
        }
    }
}