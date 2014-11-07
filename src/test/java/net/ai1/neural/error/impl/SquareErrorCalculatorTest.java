package net.ai1.neural.error.impl;

import net.ai1.neural.error.ErrorCalculator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class SquareErrorCalculatorTest {

    @Test
    public void shouldCalculateProperErrorValue() {
        //given
        ErrorCalculator errorCalculator = new SquareErrorCalculator();
        //when
        Double error = errorCalculator.calculate(new double[]{2,2}, new double[]{4, 4});
        //then
        assertThat(error).isEqualTo(4, offset(0.001));
    }
}
