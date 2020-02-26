package ru.starovoytov.calc;

import java.math.BigDecimal;

/**
 * Строка графика платежей
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class Row {
	private final int rowNum;
	private final BigDecimal mainPay;
	private final BigDecimal ratePay;
	private final BigDecimal mainAmount;
	private final BigDecimal fullPay;
	private final int month;
	private final int year;

	/**
	 * Конструктор
	 *
	 * @param rowNum     номер платежа
	 * @param mainPay    платёж по основному долгу
	 * @param ratePay    платёж по процентам
	 * @param mainAmount остаток основного долга
	 * @param fullPay    общая сумма платежа
	 * @param month      месяц платежа
	 * @param year       год платежа
	 */
	public Row(int rowNum, BigDecimal mainPay, BigDecimal ratePay, BigDecimal mainAmount,
		BigDecimal fullPay, int month, int year) {
		this.rowNum = rowNum;
		this.mainPay = mainPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.ratePay = ratePay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.mainAmount = mainAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.fullPay = fullPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.month = month + 1;
		this.year = year;
	}

	public int getRowNum() {
		return rowNum;
	}

	public BigDecimal getMainPay() {
		return mainPay;
	}

	public BigDecimal getRatePay() {
		return ratePay;
	}

	public BigDecimal getMainAmount() {
		return mainAmount;
	}

	public BigDecimal getFullPay() {
		return fullPay;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "Row{" + "rowNum=" + rowNum + ", mainPay=" + mainPay + ", ratePay=" + ratePay + ", mainAmount=" + mainAmount + ", fullPay=" + fullPay + ", month=" + month + ", year=" + year + '}';
	}
}
