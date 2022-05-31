package advancedcalculate.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OperandTest {
    @Test
    fun `합 연산자 오버라이딩이 정상적으로 동작한다1`() {
        val operand = Operand(1.0) + Operand(2.0)

        assertThat(operand.value).isEqualTo(3.0)
    }

    @Test
    fun `합 연산자 오버라이딩이 정상적으로 동작한다2`() {
        var operand = Operand(1.0)
        operand += Operand(2.0)

        assertThat(operand.value).isEqualTo(3.0)
    }

    @Test
    fun `음수 Operand는 만들어질 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Operand(-1.0)
        }
    }
}
