package ru.starovoytov.calc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

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
	BigDecimal p;

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

		paySum = calculatePaySum().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	private BigDecimal calculatePaySum() {
		BigDecimal bigRate = new BigDecimal(rate).divide(new BigDecimal(100, MathContext.UNLIMITED), MathContext.UNLIMITED);
		p = bigRate.divide(new BigDecimal(12, MathContext.UNLIMITED), MathContext.UNLIMITED);

		return p.divide(p.add(BigDecimal.ONE, MathContext.UNLIMITED)
			                .pow(monthCount, MathContext.UNLIMITED)
			                .subtract(BigDecimal.ONE, MathContext.UNLIMITED), 50, BigDecimal.ROUND_HALF_UP)
			.add(p, MathContext.UNLIMITED)
			.multiply(new BigDecimal(amount, MathContext.UNLIMITED), MathContext.UNLIMITED);
	}

	public BigDecimal getPaySum() {
		return paySum;
	}

	public List<Row> getPaySchedule() {
		List<Row> schedule = new ArrayList<>();

		BigDecimal currentAmount = new BigDecimal(amount);

		for (int i = 0; i < monthCount; i++) {
			BigDecimal currentPayRate = currentAmount.multiply(p, MathContext.UNLIMITED);
			BigDecimal currentPayAmount = paySum.subtract(currentPayRate, MathContext.UNLIMITED);
			currentAmount = currentAmount.subtract(currentPayAmount, MathContext.UNLIMITED);
			schedule.add(new Row(i + 1, currentPayAmount, currentPayRate, currentAmount, paySum));
		}

		return schedule;
	}
}
