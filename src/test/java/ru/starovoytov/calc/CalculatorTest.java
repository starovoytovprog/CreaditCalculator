package ru.starovoytov.calc;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Тест кредитного калькулятора {@link Calculator}
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class CalculatorTest {

	/**
	 * Проверка простого рассчёта ежемесячного платежа
	 */
	@Test
	public void calcSimpleTest() {
		Calculator calculator = new Calculator(100_000, 1, 12);
		assertEquals(0, new BigDecimal(101_000).compareTo(calculator.getPaySum()));
	}

	/**
	 * Проверка простого рассчёта ежемесячного платежа 2
	 */
	@Test
	public void calcSimpleTest2() {
		Calculator calculator = new Calculator(250_000, 24, 15.0);

		List<Row> rows = calculator.getPaySchedule();

		for (Row row : rows) {
			assertEquals(0, row.getFullPay()
				.compareTo(row.getMainPay().add(row.getRatePay())));
		}
	}
}
