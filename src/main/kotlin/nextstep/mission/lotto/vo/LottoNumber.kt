package nextstep.mission.lotto.vo

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require((number >= 1) and (number <= 45)) { "로또 숫자는 1에서 45사이어야 합니다." }
    }
}