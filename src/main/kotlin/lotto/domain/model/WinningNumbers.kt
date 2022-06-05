package lotto.domain.model

data class WinningNumbers(
    val numbers: Lotto,
    val bonusBall: LottoNumber
) {
    init {
        require(bonusBall !in numbers.numbers) {
            MESSAGE_DUPLICATE_WINNING_NUMBERS
        }
    }

    fun checkWith(lottos: Lottos): LottoResult {
        return LottoResult.from(getLottoWinningMap(lottos))
    }

    private fun getLottoWinningMap(lottos: Lottos): Map<LottoRank, Int> {
        return lottos.value.map { lotto ->
            checkWith(lotto)
        }.groupingBy { lottoRank ->
            lottoRank
        }.eachCount().filter { (lottoRank, _) ->
            lottoRank.isWin()
        }
    }

    private fun checkWith(lotto: Lotto): LottoRank {
        val numberOfMatches = lotto.getNumberOfMatches(numbers)
        val isBonusBallMatched = isBonusBallMatched(lotto)

        return LottoRank.of(numberOfMatches, isBonusBallMatched)
    }

    private fun isBonusBallMatched(lotto: Lotto): Boolean = bonusBall in lotto

    companion object {
        private const val MESSAGE_DUPLICATE_WINNING_NUMBERS = "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
