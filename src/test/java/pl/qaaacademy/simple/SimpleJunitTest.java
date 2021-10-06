package pl.qaaacademy.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleJunitTest {
    Calculator calc = new Calculator();

    @Test
    public void addingTwoArgumentsProducesItsSum() {
        int expectedResult = 6;
        int actualResult = calc.add(2, 4);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void subtractingTwoArgumentsProducesItsSum() {
        int expectedResult = 1;
        int actualResult = calc.subtract(3, 2);
        assertEquals(actualResult, expectedResult);
    }

}
