package ru.starovoytov.calc;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Кредитный калькулятор
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class Calculator {
	private final int amount;
	private final int monthCount;
	private final double rate;

	/**
	 * Месячный аннуитетный плтёж
	 */
	private final BigDecimal paySum;

	/**
	 * Конструктор калькулятора
	 *
	 * @param amount     сумма кредита
	 * @param monthCount срок кредита в месяцах
	 * @param rate       процентная ставка
	 */
	public Calculator(int amount, int monthCount, double rate) {
		this.amount = amount;
		this.monthCount = monthCount;
		this.rate = rate;

		paySum = calculatePaySum();
	}

	private BigDecimal calculatePaySum() {
		BigDecimal bigRate = new BigDecimal(rate).divide(new BigDecimal(100));
		BigDecimal p = bigRate.divide(new BigDecimal(12, MathContext.UNLIMITED), ROUND_HALF_UP);
		return p.divide(p.add(BigDecimal.ONE)
			                .pow(monthCount)
			                .subtract(BigDecimal.ONE), ROUND_HALF_UP)
			.add(p)
			.multiply(new BigDecimal(amount));
	}

	public BigDecimal getPaySum() {
		return paySum;
	}
}
